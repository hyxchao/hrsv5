package hrds.codes;
/**Created by automatic  */
/**代码类型名：采集编码  */
public enum DataBaseCode {
	/**UTF-8<UTF_8>  */
	UTF_8("1","UTF-8","35","采集编码"),
	/**GBK<GBK>  */
	GBK("2","GBK","35","采集编码"),
	/**UTF-16<UTF_16>  */
	UTF_16("3","UTF-16","35","采集编码"),
	/**GB2312<GB2312>  */
	GB2312("4","GB2312","35","采集编码"),
	/**ISO-8859-1<ISO_8859_1>  */
	ISO_8859_1("5","ISO-8859-1","35","采集编码");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	DataBaseCode(String code,String value,String catCode,String catValue){
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
		for (DataBaseCode typeCode : DataBaseCode.values()) {
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
	public static DataBaseCode getCodeObj(String code) {
		for (DataBaseCode typeCode : DataBaseCode.values()) {
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
		return DataBaseCode.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return DataBaseCode.values()[0].getCatCode();
	}
}