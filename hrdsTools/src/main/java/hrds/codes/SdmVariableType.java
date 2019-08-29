package hrds.codes;
/**Created by automatic  */
/**代码类型名：流数据管理变量类型  */
public enum SdmVariableType {
	/**整数<ZhengShu>  */
	ZhengShu("1","整数","100","流数据管理变量类型"),
	/**字符串<ZiFuChuan>  */
	ZiFuChuan("2","字符串","100","流数据管理变量类型"),
	/**浮点数<FuDianShu>  */
	FuDianShu("3","浮点数","100","流数据管理变量类型"),
	/**字节数组<ZiJieShuZu>  */
	ZiJieShuZu("4","字节数组","100","流数据管理变量类型");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	SdmVariableType(String code,String value,String catCode,String catValue){
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
		for (SdmVariableType typeCode : SdmVariableType.values()) {
			if (typeCode.getCode().equals(code)) {
				return typeCode.value;
			}
		}
		return null;
	}

	/**根据指定的代码值转换成对象
	* @param code   本代码的代码值
	* @return
	*/
	public static SdmVariableType getCodeObj(String code) {
		for (SdmVariableType typeCode : SdmVariableType.values()) {
			if (typeCode.getCode().equals(code)) {
				return typeCode;
			}
		}
		return null;
	}

	/**
	* 获取代码项的中文类名名称
	* @return
	*/
	public static String getObjCatValue(){
		return SdmVariableType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return SdmVariableType.values()[0].getCatCode();
	}
}