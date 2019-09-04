package hrds.commons.codes;

import hrds.commons.exception.AppSystemException;
/**Created by automatic  */
/**代码类型名：对象采集方式  */
public enum ObjectCollectType {
	/**行采集<HangCaiJi>  */
	HangCaiJi("1","行采集","123","对象采集方式"),
	/**对象采集<DuiXiangCaiJi>  */
	DuiXiangCaiJi("2","对象采集","123","对象采集方式");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	ObjectCollectType(String code,String value,String catCode,String catValue){
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
		for (ObjectCollectType typeCode : ObjectCollectType.values()) {
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
	public static ObjectCollectType getCodeObj(String code) {
		for (ObjectCollectType typeCode : ObjectCollectType.values()) {
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
		return ObjectCollectType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return ObjectCollectType.values()[0].getCatCode();
	}
}