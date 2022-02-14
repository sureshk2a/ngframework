package com.uiautomation.constants;

import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.utils.PropertyUtils;

public final class FrameworkConstants {
    private FrameworkConstants(){}

    private static final String RESOURCESPATH = System.getProperty("user.dir")+ "/src/test/resources";
    private static final String CHROMEDRIVERPATH = RESOURCESPATH+"/executables/chromedriver.exe";
    private static final String CONFIGFILEPATH = RESOURCESPATH+"/config/config.properties";
    private static final int EXPLICITWAIT = 10;
    private static final String EXTENTREPORTPATH = System.getProperty("user.dir")+ "/extent-test-reports";
    private static String extentReportFilePath = "";

    public static int getEXPLICITWAIT() {
        return EXPLICITWAIT;
    }
    public static String getChromedriverPath(){
        return CHROMEDRIVERPATH;
    }
    public static String getConfigFilePath(){
        return CONFIGFILEPATH;
    }
    private static String createReportPath() throws Exception {
        if(PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
            return EXTENTREPORTPATH+"/"+System.currentTimeMillis()+"/index.html";
        }else {
            return EXTENTREPORTPATH+"/index.html";
        }
    }
    public static String getExtentReportPath() throws Exception {
        if(extentReportFilePath.isEmpty()){
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }
}
