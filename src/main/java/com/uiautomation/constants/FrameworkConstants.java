package com.uiautomation.constants;

import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;

public final class FrameworkConstants {
    private FrameworkConstants(){}

    private static final String RESOURCESPATH = System.getProperty("user.dir")+ "/src/test/resources";
    private static final String CHROMEDRIVERPATH = RESOURCESPATH+"/executables/chromedriver.exe";
    private static final String GECKODRIVERPATH = RESOURCESPATH+"/executables/geckodriver.exe";
    private static final String CONFIGFILEPATH = RESOURCESPATH+"/config/config.properties";
    private static final int EXPLICITWAIT = 10;
    private static final String EXTENTREPORTPATH = System.getProperty("user.dir")+ "/extent-test-reports";
    private static final String EXCELPATH = RESOURCESPATH + "/excel/testdata.xlsx";
    private static String extentReportFilePath = "";
	private static final String RUNMANAGERSHEET = "RUNMANAGER";
    private static final String DATASHEET = "DATA";
    

    public static String getRunManagerSheet() {
  		return RUNMANAGERSHEET;
  	}
  	public static String getDataSheet() {
  		return DATASHEET;
  	}
    public static int getEXPLICITWAIT() {
        return EXPLICITWAIT;
    }
    public static String getChromedriverPath(){
        return CHROMEDRIVERPATH;
    }
    public static String getGeckodriverPath(){
        return GECKODRIVERPATH;
    }
    public static String getConfigFilePath(){
        return CONFIGFILEPATH;
    }
    private static String createReportPath() {
        if(PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
            return EXTENTREPORTPATH+"/"+System.currentTimeMillis()+"/index.html";
        }else {
            return EXTENTREPORTPATH+"/index.html";
        }
    }
    public static String getExtentReportPath() {
        if(extentReportFilePath.isEmpty()){
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }
    public static String getExcelPath(){
        return EXCELPATH;
    }
}
