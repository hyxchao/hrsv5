package hrds.agent.job.biz.core.dbstage.service;

import hrds.agent.job.biz.bean.ColumnCleanResult;
import hrds.agent.job.biz.bean.ColumnSplitBean;
import hrds.agent.job.biz.bean.JobInfo;
import hrds.agent.job.biz.bean.TableCleanResult;
import hrds.agent.job.biz.constant.FileFormatConstant;
import hrds.agent.job.biz.constant.IsFlag;
import hrds.agent.job.biz.constant.JobConstant;
import hrds.agent.job.biz.core.dbstage.writer.DBCollCSVWriter;
import hrds.agent.job.biz.core.dbstage.writer.DBCollParquetWriter;
import hrds.agent.job.biz.core.dbstage.writer.FileWriterInterface;
import hrds.agent.job.biz.utils.ColumnTool;
import hrds.agent.job.biz.utils.ParquetUtil;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.schema.MessageType;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ResultSetParser <br/>
 * Function: 每个采集线程分别调用，用于解析当前线程采集到的ResultSet,并根据卸数的数据文件类型，调用相应的方法写数据文件. <br/>
 * Reason: 数据库直连采集
 * Date: 2019/8/1 15:24 <br/>
 * <p>
 * Author WangZhengcheng
 * Version 1.0
 * Since JDK 1.8
 **/
public class ResultSetParser {
    /** 
    * @Description: 解析ResultSet
    * @Param: [rs : 数据集]
    * @Param: [jobInfo : 采集任务信息]
    * @Param: [pageNum : 分页的页数]
    * @Param: [pageRow : 每页的数据量，和分页页数一起，在写文件的时候，如果文件过大，要进行单个文件的拆分时使用]
    * @return: java.lang.String
    * @Author: WangZhengcheng 
    * @Date: 2019/9/2 
    */
    /*
    * 1、获得本次采集的数据库META信息
    * 2、对后续需要使用的META信息(列名，列类型，列长度)，使用分隔符进行组装
    * 3、在jobInfo中拿到数据清洗规则(字段清洗，表清洗)，并调用工具类(ColCleanRuleParser，TbCleanRuleParser)中的方法进行解析
    * 4、如果在表清洗中进行了列合并，调用工具类ColumnTool对组装好的META信息进行更新
    * 5、如果在列清洗中进行了列拆分，调用工具类ColumnTool对组装好的META信息进行更新
    * 6、落地文件需要追加开始时间和结束时间(9999-12-31)列，如果需要，还要追加MD5列
    * 7、构造metaDataMap，根据落地数据文件类型，初始化FileWriterInterface实现类，由实现类去写文件
    * 8、写文件结束，返回本线程生成数据文件的路径
    * */
    public String parseResultSet(ResultSet rs, JobInfo jobInfo, int pageNum, int pageRow) throws SQLException, IOException{
        //TODO 建议查询数据库的系统表来获得meta信息
        //1、获得本次采集的数据库META信息
        ResultSetMetaData metaData = rs.getMetaData();
        //获得列的数量
        int columnCount = metaData.getColumnCount();
        //用于保存列名
        StringBuilder columns = new StringBuilder();
        //用于保存所有列数据类型和长度
        StringBuilder columnsTypeAndPreci = new StringBuilder();
        //用于保存列长度
        StringBuilder columnsLength = new StringBuilder();
        //用于存放所有列数据类型，初始长度为columnCount
        int[] colTypeArr = new int[columnCount];
        //2、对后续需要使用的META信息，使用分隔符进行组装
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            int columnType = metaData.getColumnType(i);
            colTypeArr[i - 1] = columnType;
            //列名拼接使用'\001'分隔
            columns.append(columnName).append(JobConstant.COLUMN_NAME_SEPARATOR);
            String colTypeAndPreci = getColTypeAndPreci(metaData.getColumnType(i), metaData.getColumnTypeName(i), metaData.getPrecision(i), metaData.getScale(i));
            columnsTypeAndPreci.append(colTypeAndPreci).append(JobConstant.COLUMN_TYPE_SEPARATOR);
            columnsLength.append(getColumnLength(metaData, i)).append(JobConstant.COLUMN_TYPE_SEPARATOR);
        }
        //得到表元信息后，需要去掉最后一个分隔符
        columns.deleteCharAt(columns.length() - 1);//列名
        columnsTypeAndPreci.deleteCharAt(columnsTypeAndPreci.length() - 1);//列类型(长度,精度)
        columnsLength.deleteCharAt(columnsLength.length() - 1);//列长度
        //3、在jobInfo中拿到数据清洗规则(字段清洗，表清洗)，并调用工具类(ColCleanRuleParser，TbCleanRuleParser)中的方法进行解析
        //获得采集每一列的清洗规则
        List<ColumnCleanResult> colCleanRuleList = jobInfo.getColumnList();
        //存放列清洗规则，key为列名，value为清洗方式map，map的key为清洗项目名(优先级、替换、补齐等)，value为具体的清洗信息
        Map<String,Map<String, Object>> columnCleanRule = new HashMap<>();
        for (int i = 0; i <= colCleanRuleList.size(); i++) {
            Map<String, Object> columnResult = ColCleanRuleParser.parseColCleanRule(colCleanRuleList.get(i));
            columnCleanRule.put(colCleanRuleList.get(i).getColumnName(),columnResult);
        }

        //用于存放该张表所有的列拆分信息，key为字段原名，value为对该字段的拆分规则
        Map<String, List<ColumnSplitBean>> allSplit = new LinkedHashMap<>();
        for(int i = 0; i < colCleanRuleList.size(); i++){
            String columnName = colCleanRuleList.get(i).getColumnName();
            Map<String, Object> rule = (Map<String, Object>)columnCleanRule.get(columnName);
            List<ColumnSplitBean> columnSplitBeanList = (List<ColumnSplitBean>)rule.get("split");
            if(columnSplitBeanList != null && !columnSplitBeanList.isEmpty()){
                allSplit.put(columnName, columnSplitBeanList);
            }
        }

        //获得整表的清洗规则
        TableCleanResult tbCleanResult = jobInfo.getTbCleanResult();
        //将整表清洗规则进行解析
        Map<String, Object> tbCleanRule = TbCleanRuleParser.parseTbCleanRule(tbCleanResult);
        //得到列合并规则
        Map<String, String> tbMergeRule = (Map<String, String>) tbCleanRule.get("merge");

        //4、如果在表清洗中进行了列合并，调用工具类ColumnTool对组装好的META信息进行更新
        if(tbMergeRule != null && !tbMergeRule.isEmpty()){
            //调用方法更新列合并后的columns, columnsTypeAndPreci, columnsLength
            ColumnTool.updateColumnMerge(columns, columnsTypeAndPreci, columnsLength, tbMergeRule);
        }

        //5、如果在列清洗中进行了列拆分，调用工具类ColumnTool对组装好的META信息进行更新
        if(!allSplit.isEmpty()){
            //调用方法更新列拆分后的columns, columnsTypeAndPreci, columnsLength
            ColumnTool.updateColumnSplit(columns, columnsTypeAndPreci, columnsLength, allSplit);
        }

        //6、落地文件需要追加开始时间和结束时间(9999-12-31)列，如果需要，还要追加MD5列
        columnsTypeAndPreci.append(JobConstant.COLUMN_TYPE_SEPARATOR).append("char(8)").append(JobConstant.COLUMN_TYPE_SEPARATOR).append("char(8)");
        columnsLength.append(JobConstant.COLUMN_TYPE_SEPARATOR).append("8").append(JobConstant.COLUMN_TYPE_SEPARATOR).append("8");
        columns.append(JobConstant.COLUMN_NAME_SEPARATOR).append(JobConstant.START_DATE_NAME).append(JobConstant.COLUMN_NAME_SEPARATOR).append(JobConstant.MAX_DATE_NAME);

        //如果用户需要追加MD5，则需要再添加一列
        String isMD5 = jobInfo.getIs_md5();
        if(isMD5 != null && !isMD5.isEmpty()){
            if(IsFlag.YES.getCode() == Integer.parseInt(isMD5)){
                columnsTypeAndPreci.append(JobConstant.COLUMN_TYPE_SEPARATOR).append("char(32)");
                columnsLength.append(JobConstant.COLUMN_TYPE_SEPARATOR).append("32");
                columns.append(JobConstant.COLUMN_NAME_SEPARATOR).append(JobConstant.MD5_NAME);
            }
        }
        //7、构造metaDataMap，根据落地数据文件类型，初始化FileWriterInterface实现类，由实现类去写文件
        Map<String,Object> metaDataMap = new HashMap<>();
        //列数据类型(长度,精度)
        metaDataMap.put("columnsTypeAndPreci", columnsTypeAndPreci);
        //列长度，在生成信号文件的时候需要使用，目前暂时不需要
        metaDataMap.put("columnsLength" ,columnsLength);
        //列名
        metaDataMap.put("columns", columns);
        //列数据类型(java.sql.Types)
        metaDataMap.put("colTypeArr", colTypeArr);
        //列数量
        metaDataMap.put("columnCount", columnCount);
        //每列的清洗规则
        metaDataMap.put("columnCleanRule", columnCleanRule);
        //整表的清洗规则
        metaDataMap.put("tableCleanRule", tbCleanRule);

        //获得数据文件格式
        String format = jobInfo.getFile_format();
        if (format == null || format.isEmpty()) {
            throw new RuntimeException("HDFS文件类型不能为空");
        }
        //当前线程生成的数据文件的路径，用于返回
        String filePath = "";
        if(FileFormatConstant.CSV.getCode() == Integer.parseInt(format)){
            //写CSV文件
            FileWriterInterface csvWriter = new DBCollCSVWriter(jobInfo, pageNum, pageRow);
            filePath = csvWriter.writeDataAsSpecifieFormat(metaDataMap, rs, jobInfo.getTable_name());
        }else if(FileFormatConstant.PARQUET.getCode() == Integer.parseInt(format)){
            //写PARQUET文件
            MessageType schema = ParquetUtil.getSchemaAsDBColl(columns.toString(), columnsTypeAndPreci.toString());
            GroupFactory factory = new SimpleGroupFactory(schema);
            FileWriterInterface parquetWriter = new DBCollParquetWriter(jobInfo, schema, factory, pageNum, pageRow);
            filePath = parquetWriter.writeDataAsSpecifieFormat(metaDataMap, rs, jobInfo.getTable_name());
        }else if(FileFormatConstant.ORCFILE.getCode() == Integer.parseInt(format)){
            //写ORC文件
        }else if(FileFormatConstant.SEQUENCEFILE.getCode() == Integer.parseInt(format)){
            //写SEQUENCE文件
        }
        //8、写文件结束，返回本线程生成数据文件的路径
        return filePath;
    }

    /**
     * 获取数据库列数据类型和长度精度
     *
     * @param columnType     {@link Integer} sql.Types的数据类型
     * @param columnTypeName {@link String} 列数据类型
     * @param precision      {@link String} 列长度
     * @param scale          {@link String} 列数据精度
     */
    /*
    * 1、考虑到有些类型在数据库中在获取数据类型的时候就会带有(),同时还能获取到数据的长度和精度，因此我们要对所有数据库进行统一处理，去掉()中的内容，使用JDBC提供的方法读取的长度和精度进行拼接
    * 2、对不包含长度和精度的数据类型进行处理，返回数据类型
    * 3、对包含长度和精度的数据类型进行处理，返回数据类型(长度,精度)
    * 4、对只包含长度的数据类型进行处理，返回数据类型(长度)
    * */
    private String getColTypeAndPreci(int columnType, String columnTypeName, int precision, int scale) {
        //1、考虑到有些类型在数据库中在获取数据类型的时候就会带有(),同时还能获取到数据的长度和精度，因此我们要对所有数据库进行统一处理，去掉()中的内容，使用JDBC提供的方法读取的长度和精度进行拼接
        if (precision != 0) {
            int index = columnTypeName.indexOf("(");
            if (index != -1) {
                columnTypeName = columnTypeName.substring(0, index);
            }
        }
        String colTypeAndPreci;
        if (Types.INTEGER == columnType || Types.TINYINT == columnType || Types.SMALLINT == columnType || Types.BIGINT == columnType) {
            //2、上述数据类型不包含长度和精度
            colTypeAndPreci = columnTypeName;
        } else if (Types.NUMERIC == columnType || Types.FLOAT == columnType || Types.DOUBLE == columnType || Types.DECIMAL == columnType) {
            //上述数据类型包含长度和精度，对长度和精度进行处理，返回(长度,精度)
            //1、当一个数的整数部分的长度 > p-s 时，Oracle就会报错
            //2、当一个数的小数部分的长度 > s 时，Oracle就会舍入。
            //3、当s(scale)为负数时，Oracle就对小数点左边的s个数字进行舍入。
            //4、当s > p 时, p表示小数点后第s位向左最多可以有多少位数字，如果大于p则Oracle报错，小数点后s位向右的数字被舍入
            if (precision > precision - Math.abs(scale) || scale > precision || precision == 0) {
                precision = 38;
                scale = 12;
            }
            colTypeAndPreci = columnTypeName + "(" + precision + "," + scale + ")";
        } else {
            //处理字符串类型，只包含长度,不包含精度
            if ("char".equalsIgnoreCase(columnTypeName) && precision > 255) {
                columnTypeName = "varchar";
            }
            colTypeAndPreci = columnTypeName + "(" + precision + ")";
        }
        return colTypeAndPreci;
    }

    /**
     * 获取数据库表中每一列列的长度
     *
     * @param rsMetaData {@link ResultSetMetaData}
     * @param index      {@link int}
     */
    private int getColumnLength(ResultSetMetaData rsMetaData, int index) throws SQLException {
        int columnLength = rsMetaData.getPrecision(index);
        String columnType = rsMetaData.getColumnTypeName(index).toUpperCase();
        if (columnType.equals("DECIMAL") || columnType.equals("NUMERIC")) {
            columnLength = columnLength + 2;
        }
        return columnLength;
    }
}
