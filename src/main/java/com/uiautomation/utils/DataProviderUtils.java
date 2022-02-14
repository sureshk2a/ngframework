package com.uiautomation.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {

    @DataProvider
    public static Object[] getData(Method m) throws IOException {

        String testName = m.getName();

        List<Map<String, String>> list= ExcelUtils.getTestDetails("DATA");
        List<Map<String, String>> iterationList = new ArrayList<>();

        for (Map<String, String> dataMap : list) {
            if (dataMap.get("testname").equalsIgnoreCase(testName) && dataMap.get("execute").equalsIgnoreCase("yes")) {
                    iterationList.add(dataMap);
            }
        }

        return iterationList.toArray();
    }

}
