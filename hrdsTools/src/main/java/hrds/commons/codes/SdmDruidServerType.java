package hrds.commons.codes;

import hrds.commons.exception.AppSystemException;
/**Created by automatic  */
/**代码类型名：流数据管理druid服务类型  */
public enum SdmDruidServerType {
	/**索引服务<IndexingServer>  */
	IndexingServer("1","索引服务","136","流数据管理druid服务类型"),
	/**实时服务<realtimeServer>  */
	realtimeServer("2","实时服务","136","流数据管理druid服务类型");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	SdmDruidServerType(String code,String value,String catCode,String catValue){
		this.code = code;
		this.value = value;
		this.catCode = catCode;
		this.catValue = catValue;
	}
	public String getCode(){return code;}
	public String getValue(){return value;}
	public String getCatCode(){return catCode;}
	public String getCatValue(){return catValue;}

	/**根据指定的代码值转换成中文名字
	* @param code   本代码的代码值
	* @return
	*/
	public static String getValue(String code) {
		for (SdmDruidServerType typeCode : SdmDruidServerType.values()) {
			if (typeCode.getCode().equals(code)) {
				return typeCode.value;
			}
		}
		throw new AppSystemException("根据"+code+"没有找到对应的代码项");
	}

	/**根据指定的代码值转换成对象
	* @param code   本代码的代码值
	* @return
	*/
	public static SdmDruidServerType getCodeObj(String code) {
		for (SdmDruidServerType typeCode : SdmDruidServerType.values()) {
			if (typeCode.getCode().equals(code)) {
				return typeCode;
			}
		}
		throw new AppSystemException("根据"+code+"没有找到对应的代码项");
	}

	/**
	* 获取代码项的中文类名名称
	* @return
	*/
	public static String getObjCatValue(){
		return SdmDruidServerType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return SdmDruidServerType.values()[0].getCatCode();
	}
}