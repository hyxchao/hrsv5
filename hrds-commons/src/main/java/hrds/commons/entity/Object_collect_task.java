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
 * 对象采集对应信息
 */
@Table(tableName = "object_collect_task")
public class Object_collect_task extends TableEntity
{
	private static final long serialVersionUID = 321566870187324L;
	private transient static final Set<String> __PrimaryKeys;
	public static final String TableName = "object_collect_task";
	/**
	* 检查给定的名字，是否为主键中的字段
	* @param name String 检验是否为主键的名字
	* @return
	*/
	public static boolean isPrimaryKey(String name) { return __PrimaryKeys.contains(name); } 
	public static Set<String> getPrimaryKeyNames() { return __PrimaryKeys; } 
	/** 对象采集对应信息 */
	static {
		Set<String> __tmpPKS = new HashSet<>();
		__tmpPKS.add("ocs_id");
		__PrimaryKeys = Collections.unmodifiableSet(__tmpPKS);
	}
	private Long ocs_id; //对象采集任务编号
	private String en_name; //英文名称
	private String zh_name; //中文名称
	private String remark; //备注
	private String collect_data_type; //数据类型
	private Long odc_id; //对象采集id
	private Long agent_id; //Agent_id
	private String database_code; //采集编码

	/** 取得：对象采集任务编号 */
	public Long getOcs_id(){
		return ocs_id;
	}
	/** 设置：对象采集任务编号 */
	public void setOcs_id(Long ocs_id){
		this.ocs_id=ocs_id;
	}
	/** 设置：对象采集任务编号 */
	public void setOcs_id(String ocs_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(ocs_id)){
			this.ocs_id=new Long(ocs_id);
		}
	}
	/** 取得：英文名称 */
	public String getEn_name(){
		return en_name;
	}
	/** 设置：英文名称 */
	public void setEn_name(String en_name){
		this.en_name=en_name;
	}
	/** 取得：中文名称 */
	public String getZh_name(){
		return zh_name;
	}
	/** 设置：中文名称 */
	public void setZh_name(String zh_name){
		this.zh_name=zh_name;
	}
	/** 取得：备注 */
	public String getRemark(){
		return remark;
	}
	/** 设置：备注 */
	public void setRemark(String remark){
		this.remark=remark;
	}
	/** 取得：数据类型 */
	public String getCollect_data_type(){
		return collect_data_type;
	}
	/** 设置：数据类型 */
	public void setCollect_data_type(String collect_data_type){
		this.collect_data_type=collect_data_type;
	}
	/** 取得：对象采集id */
	public Long getOdc_id(){
		return odc_id;
	}
	/** 设置：对象采集id */
	public void setOdc_id(Long odc_id){
		this.odc_id=odc_id;
	}
	/** 设置：对象采集id */
	public void setOdc_id(String odc_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(odc_id)){
			this.odc_id=new Long(odc_id);
		}
	}
	/** 取得：Agent_id */
	public Long getAgent_id(){
		return agent_id;
	}
	/** 设置：Agent_id */
	public void setAgent_id(Long agent_id){
		this.agent_id=agent_id;
	}
	/** 设置：Agent_id */
	public void setAgent_id(String agent_id){
		if(!fd.ng.core.utils.StringUtil.isEmpty(agent_id)){
			this.agent_id=new Long(agent_id);
		}
	}
	/** 取得：采集编码 */
	public String getDatabase_code(){
		return database_code;
	}
	/** 设置：采集编码 */
	public void setDatabase_code(String database_code){
		this.database_code=database_code;
	}
}