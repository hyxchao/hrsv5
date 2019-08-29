package hrds.entity;
/**Auto Created by VBScript Do not modify!*/
import fd.ng.db.entity.TableEntity;
import fd.ng.core.utils.StringUtil;
import fd.ng.db.entity.anno.Column;
import fd.ng.db.entity.anno.Table;
import hrds.exception.BusinessException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * 模版作业信息表
 */
@Table(tableName = "etl_job_temp")
public class Etl_job_temp extends TableEntity
{
	private static final long serialVersionUID = 321566870187324L;
	private transient static final Set<String> __PrimaryKeys;
	public static final String TableName = "etl_job_temp";
	/**
	* 检查给定的名字，是否为主键中的字段
	* @param name String 检验是否为主键的名字
	* @return
	*/
	public static boolean isPrimaryKey(String name) { return __PrimaryKeys.contains(name); } 
	public static Set<String> getPrimaryKeyNames() { return __PrimaryKeys; } 
	/** 模版作业信息表 */
	static {
		Set<String> __tmpPKS = new HashSet<>();
		__tmpPKS.add("etl_temp_id");
		__PrimaryKeys = Collections.unmodifiableSet(__tmpPKS);
	}
	private Long etl_temp_id; //模版ID
	private String etl_temp_type; //模版名称
	private String pro_dic; //模版shell路径
	private String pro_name; //模版shell名称

	/** 取得：模版ID */
	public Long getEtl_temp_id(){
		return etl_temp_id;
	}
	/** 设置：模版ID */
	public void setEtl_temp_id(Long etl_temp_id){
		this.etl_temp_id=etl_temp_id;
	}
	/** 设置：模版ID */
	public void setEtl_temp_id(String etl_temp_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(etl_temp_id)){
			this.etl_temp_id=new Long(etl_temp_id);
		}
	}
	/** 取得：模版名称 */
	public String getEtl_temp_type(){
		return etl_temp_type;
	}
	/** 设置：模版名称 */
	public void setEtl_temp_type(String etl_temp_type){
		this.etl_temp_type=etl_temp_type;
	}
	/** 取得：模版shell路径 */
	public String getPro_dic(){
		return pro_dic;
	}
	/** 设置：模版shell路径 */
	public void setPro_dic(String pro_dic){
		this.pro_dic=pro_dic;
	}
	/** 取得：模版shell名称 */
	public String getPro_name(){
		return pro_name;
	}
	/** 设置：模版shell名称 */
	public void setPro_name(String pro_name){
		this.pro_name=pro_name;
	}
}