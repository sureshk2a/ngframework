package com.uiautomation.utils;

import org.testng.annotations.DataProvider;

import com.uiautomation.constants.FrameworkConstants;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {

	private static List<Map<String, String>> list = new ArrayList<>();
	
    @DataProvider
    public static Object[] getData(Method m)  {

        String testName = m.getName();
        
        if(list.isEmpty()) {
        	try {
				list= ExcelUtils.getTestDetails(FrameworkConstants.getDataSheet());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        List<Map<String, String>> smalllist = new ArrayList<>();

        for (Map<String, String> dataMap : list) {
            if (dataMap.get("testname").equalsIgnoreCase(testName) && dataMap.get("execute").equalsIgnoreCase("yes")) {
                smalllist.add(dataMap);
            }
        }
        return smalllist.toArray();
    }

}
