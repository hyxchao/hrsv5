package hrds.codes;
/**Created by automatic  */
/**代码类型名：隐藏层单位类型  */
public enum HideLayerUnitType {
	/**自动计算<ZiDongJiSuan>  */
	ZiDongJiSuan("1","自动计算","77","隐藏层单位类型"),
	/**设定<SheDing>  */
	SheDing("2","设定","77","隐藏层单位类型");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	HideLayerUnitType(String code,String value,String catCode,String catValue){
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
		for (HideLayerUnitType typeCode : HideLayerUnitType.values()) {
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
	public static HideLayerUnitType getCodeObj(String code) {
		for (HideLayerUnitType typeCode : HideLayerUnitType.values()) {
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
		return HideLayerUnitType.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String getObjCatCode(){
		return HideLayerUnitType.values()[0].getCatCode();
	}
}