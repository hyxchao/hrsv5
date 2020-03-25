package hrds.commons.codes;

import hrds.commons.exception.AppSystemException;
/**Created by automatic  */
/**代码类型名：作业运行状态  */
public enum JobExecuteState {
	/**等待<DengDai>  */
	DengDai("100","等待","87","作业运行状态"),
	/**运行<YunXing>  */
	YunXing("101","运行","87","作业运行状态"),
	/**暂停<ZanTing>  */
	ZanTing("102","暂停","87","作业运行状态"),
	/**中止<ZhongZhi>  */
	ZhongZhi("103","中止","87","作业运行状态"),
	/**完成<WanCheng>  */
	WanCheng("104","完成","87","作业运行状态"),
	/**失败<ShiBai>  */
	ShiBai("105","失败","87","作业运行状态");

	private final String code;
	private final String value;
	private final String catCode;
	private final String catValue;

	JobExecuteState(String code,String value,String catCode,String catValue){
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
	public static String ofValueByCode(String code) {
		for (JobExecuteState typeCode : JobExecuteState.values()) {
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
	public static JobExecuteState ofEnumByCode(String code) {
		for (JobExecuteState typeCode : JobExecuteState.values()) {
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
	public static String ofCatValue(){
		return JobExecuteState.values()[0].getCatValue();
	}

	/**
	* 获取代码项的分类代码
	* @return
	*/
	public static String ofCatCode(){
		return JobExecuteState.values()[0].getCatCode();
	}

	/**
	* 禁止使用类的tostring()方法
	* @return
	*/
	@Override
	public String toString() {
		throw new AppSystemException("There's no need for you to !");
	}
}