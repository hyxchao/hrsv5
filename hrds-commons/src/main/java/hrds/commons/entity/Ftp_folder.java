package hrds.commons.entity;
/**Auto Created by VBScript Do not modify!*/
import hrds.commons.entity.fdentity.ProjectTableEntity;
import fd.ng.db.entity.anno.Table;
import fd.ng.core.annotation.DocBean;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * ftp目录表
 */
@Table(tableName = "ftp_folder")
public class Ftp_folder extends ProjectTableEntity
{
	private static final long serialVersionUID = 321566870187324L;
	private transient static final Set<String> __PrimaryKeys;
	public static final String TableName = "ftp_folder";
	/**
	* 检查给定的名字，是否为主键中的字段
	* @param name String 检验是否为主键的名字
	* @return
	*/
	public static boolean isPrimaryKey(String name) { return __PrimaryKeys.contains(name); } 
	public static Set<String> getPrimaryKeyNames() { return __PrimaryKeys; } 
	/** ftp目录表 */
	static {
		Set<String> __tmpPKS = new HashSet<>();
		__tmpPKS.add("ftp_folder_id");
		__PrimaryKeys = Collections.unmodifiableSet(__tmpPKS);
	}
	@DocBean(name ="ftp_folder_id",value="目录表id:",dataType = Long.class,required = true)
	private Long ftp_folder_id;
	@DocBean(name ="ftp_folder_name",value="ftp目录名称:",dataType = String.class,required = true)
	private String ftp_folder_name;
	@DocBean(name ="is_processed",value="是否处理过(IsFlag):1-是<Shi> 0-否<Fou> ",dataType = String.class,required = true)
	private String is_processed;
	@DocBean(name ="ftp_date",value="ftp日期:",dataType = String.class,required = true)
	private String ftp_date;
	@DocBean(name ="ftp_time",value="ftp时间:",dataType = String.class,required = true)
	private String ftp_time;
	@DocBean(name ="remark",value="备注:",dataType = String.class,required = false)
	private String remark;
	@DocBean(name ="ftp_id",value="ftp采集id:",dataType = Long.class,required = true)
	private Long ftp_id;

	/** 取得：目录表id */
	public Long getFtp_folder_id(){
		return ftp_folder_id;
	}
	/** 设置：目录表id */
	public void setFtp_folder_id(Long ftp_folder_id){
		this.ftp_folder_id=ftp_folder_id;
	}
	/** 设置：目录表id */
	public void setFtp_folder_id(String ftp_folder_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(ftp_folder_id)){
			this.ftp_folder_id=new Long(ftp_folder_id);
		}
	}
	/** 取得：ftp目录名称 */
	public String getFtp_folder_name(){
		return ftp_folder_name;
	}
	/** 设置：ftp目录名称 */
	public void setFtp_folder_name(String ftp_folder_name){
		this.ftp_folder_name=ftp_folder_name;
	}
	/** 取得：是否处理过 */
	public String getIs_processed(){
		return is_processed;
	}
	/** 设置：是否处理过 */
	public void setIs_processed(String is_processed){
		this.is_processed=is_processed;
	}
	/** 取得：ftp日期 */
	public String getFtp_date(){
		return ftp_date;
	}
	/** 设置：ftp日期 */
	public void setFtp_date(String ftp_date){
		this.ftp_date=ftp_date;
	}
	/** 取得：ftp时间 */
	public String getFtp_time(){
		return ftp_time;
	}
	/** 设置：ftp时间 */
	public void setFtp_time(String ftp_time){
		this.ftp_time=ftp_time;
	}
	/** 取得：备注 */
	public String getRemark(){
		return remark;
	}
	/** 设置：备注 */
	public void setRemark(String remark){
		this.remark=remark;
	}
	/** 取得：ftp采集id */
	public Long getFtp_id(){
		return ftp_id;
	}
	/** 设置：ftp采集id */
	public void setFtp_id(Long ftp_id){
		this.ftp_id=ftp_id;
	}
	/** 设置：ftp采集id */
	public void setFtp_id(String ftp_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(ftp_id)){
			this.ftp_id=new Long(ftp_id);
		}
	}
}
