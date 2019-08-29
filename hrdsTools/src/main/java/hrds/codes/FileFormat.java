package hrds.codes;
/**Created by automatic  */
/**代码类型名：DB文件格式  */
public enum FileFormat {
	/**定长<DingChang>  */
	DingChang("0","定长","47","DB文件格式"),
	/**非定长<FeiDingChang>  */
	FeiDingChang("1","非定长","47","DB文件格式"),
	/**CSV<CSV>  */
	CSV("2","CSV","47","DB文件格式");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	FileFormat(String code,String value,String catCode,String catValue){
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
		for (FileFormat typeCode : FileFormat.values()) {
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
	public static FileFormat getCodeObj(String code) {
		for (FileFormat typeCode : FileFormat.values()) {
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
		return FileFormat.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return FileFormat.values()[0].getCatCode();
	}
}