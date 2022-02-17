package com.uiautomation.utils;

public final class DynamicXpathUtils {

	private DynamicXpathUtils() {}
	
	public static String getXpath(String xpath, String value) {
		return String.format(xpath, value);
	}
	
	public static String getXpath(String xpath, String value1,String value2) {
		return String.format(xpath, value1,value2);
	}
	
	public static String getXpath(String xpath, String value1,String value2,String value3) {
		return String.format(xpath, value1, value2, value3);
	}
	
	
}
