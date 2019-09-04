package hrds.commons.entity;
/**Auto Created by VBScript Do not modify!*/
import fd.ng.db.entity.TableEntity;
import fd.ng.core.utils.StringUtil;
import fd.ng.db.entity.anno.Column;
import fd.ng.db.entity.anno.Table;
import hrds.commons.exception.BusinessException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * 部门信息表
 */
@Table(tableName = "department_info")
public class Department_info extends TableEntity
{
	private static final long serialVersionUID = 321566870187324L;
	private transient static final Set<String> __PrimaryKeys;
	public static final String TableName = "department_info";
	/**
	* 检查给定的名字，是否为主键中的字段
	* @param name String 检验是否为主键的名字
	* @return
	*/
	public static boolean isPrimaryKey(String name) { return __PrimaryKeys.contains(name); } 
	public static Set<String> getPrimaryKeyNames() { return __PrimaryKeys; } 
	/** 部门信息表 */
	static {
		Set<String> __tmpPKS = new HashSet<>();
		__tmpPKS.add("dep_id");
		__PrimaryKeys = Collections.unmodifiableSet(__tmpPKS);
	}
	private Long dep_id; //部门ID
	private String dep_name; //部门名称
	private String create_date; //创建日期
	private String create_time; //创建时间
	private String dep_remark; //备注

	/** 取得：部门ID */
	public Long getDep_id(){
		return dep_id;
	}
	/** 设置：部门ID */
	public void setDep_id(Long dep_id){
		this.dep_id=dep_id;
	}
	/** 设置：部门ID */
	public void setDep_id(String dep_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(dep_id)){
			this.dep_id=new Long(dep_id);
		}
	}
	/** 取得：部门名称 */
	public String getDep_name(){
		return dep_name;
	}
	/** 设置：部门名称 */
	public void setDep_name(String dep_name){
		this.dep_name=dep_name;
	}
	/** 取得：创建日期 */
	public String getCreate_date(){
		return create_date;
	}
	/** 设置：创建日期 */
	public void setCreate_date(String create_date){
		this.create_date=create_date;
	}
	/** 取得：创建时间 */
	public String getCreate_time(){
		return create_time;
	}
	/** 设置：创建时间 */
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	/** 取得：备注 */
	public String getDep_remark(){
		return dep_remark;
	}
	/** 设置：备注 */
	public void setDep_remark(String dep_remark){
		this.dep_remark=dep_remark;
	}
}