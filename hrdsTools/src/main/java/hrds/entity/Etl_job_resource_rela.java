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
 * 作业资源关系表
 */
@Table(tableName = "etl_job_resource_rela")
public class Etl_job_resource_rela extends TableEntity
{
	private static final long serialVersionUID = 321566870187324L;
	private transient static final Set<String> __PrimaryKeys;
	public static final String TableName = "etl_job_resource_rela";
	/**
	* 检查给定的名字，是否为主键中的字段
	* @param name String 检验是否为主键的名字
	* @return
	*/
	public static boolean isPrimaryKey(String name) { return __PrimaryKeys.contains(name); } 
	public static Set<String> getPrimaryKeyNames() { return __PrimaryKeys; } 
	/** 作业资源关系表 */
	static {
		Set<String> __tmpPKS = new HashSet<>();
		__tmpPKS.add("etl_sys_cd");
		__tmpPKS.add("etl_job");
		__PrimaryKeys = Collections.unmodifiableSet(__tmpPKS);
	}
	private String resource_type; //资源使用类型
	private Integer resource_req; //资源需求数
	private String etl_sys_cd; //工程代码
	private String etl_job; //作业名

	/** 取得：资源使用类型 */
	public String getResource_type(){
		return resource_type;
	}
	/** 设置：资源使用类型 */
	public void setResource_type(String resource_type){
		this.resource_type=resource_type;
	}
	/** 取得：资源需求数 */
	public Integer getResource_req(){
		return resource_req;
	}
	/** 设置：资源需求数 */
	public void setResource_req(Integer resource_req){
		this.resource_req=resource_req;
	}
	/** 设置：资源需求数 */
	public void setResource_req(String resource_req){
		if(!fd.ng.core.utils.StringUtil.isEmpty(resource_req)){
			this.resource_req=new Integer(resource_req);
		}
	}
	/** 取得：工程代码 */
	public String getEtl_sys_cd(){
		return etl_sys_cd;
	}
	/** 设置：工程代码 */
	public void setEtl_sys_cd(String etl_sys_cd){
		this.etl_sys_cd=etl_sys_cd;
	}
	/** 取得：作业名 */
	public String getEtl_job(){
		return etl_job;
	}
	/** 设置：作业名 */
	public void setEtl_job(String etl_job){
		this.etl_job=etl_job;
	}
}