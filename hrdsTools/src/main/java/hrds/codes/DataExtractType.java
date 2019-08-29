package hrds.codes;
/**Created by automatic  */
/**代码类型名：数据抽取方式  */
public enum DataExtractType {
	/**仅数据抽取<JinShuJuChouQu>  */
	JinShuJuChouQu("1","仅数据抽取","145","数据抽取方式"),
	/**数据抽取及入库<ShuJuChouQuJiRuKu>  */
	ShuJuChouQuJiRuKu("2","数据抽取及入库","145","数据抽取方式");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	DataExtractType(String code,String value,String catCode,String catValue){
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
		for (DataExtractType typeCode : DataExtractType.values()) {
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
	public static DataExtractType getCodeObj(String code) {
		for (DataExtractType typeCode : DataExtractType.values()) {
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
		return DataExtractType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return DataExtractType.values()[0].getCatCode();
	}
}