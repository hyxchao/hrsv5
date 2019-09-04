package hrds.commons.codes;

import hrds.commons.exception.AppSystemException;
/**Created by automatic  */
/**代码类型名：消费周期  */
public enum ConsumerCyc {
	/**无限期<WuXianQi>  */
	WuXianQi("1","无限期","127","消费周期"),
	/**按时间结束<AnShiJianJieShu>  */
	AnShiJianJieShu("2","按时间结束","127","消费周期"),
	/**按数据量结束<AnShuJuLiangJieShu>  */
	AnShuJuLiangJieShu("3","按数据量结束","127","消费周期");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	ConsumerCyc(String code,String value,String catCode,String catValue){
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
		for (ConsumerCyc typeCode : ConsumerCyc.values()) {
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
	public static ConsumerCyc getCodeObj(String code) {
		for (ConsumerCyc typeCode : ConsumerCyc.values()) {
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
		return ConsumerCyc.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return ConsumerCyc.values()[0].getCatCode();
	}
}