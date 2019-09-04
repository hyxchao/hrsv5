package hrds.commons.codes;

import hrds.commons.exception.AppSystemException;
/**Created by automatic  */
/**代码类型名：时间类型  */
public enum TimeType {
	/**日<Day>  */
	Day("1","日","126","时间类型"),
	/**小时<Hour>  */
	Hour("2","小时","126","时间类型"),
	/**分钟<Minute>  */
	Minute("3","分钟","126","时间类型"),
	/**秒<Second>  */
	Second("4","秒","126","时间类型");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	TimeType(String code,String value,String catCode,String catValue){
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
		for (TimeType typeCode : TimeType.values()) {
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
	public static TimeType getCodeObj(String code) {
		for (TimeType typeCode : TimeType.values()) {
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
		return TimeType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return TimeType.values()[0].getCatCode();
	}
}