package com.uiautomation.listeners;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.utils.ExcelUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext iTestContext) {
        List<IMethodInstance> result = new ArrayList<IMethodInstance>();
        List<Map<String,String>> lis = null;

        try {
            lis = ExcelUtils.getTestDetails(FrameworkConstants.getRunManagerSheet());
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (IMethodInstance method : methods) { //Methods
            for (int j = 0; j < lis.size(); j++) { //User data
                if (method.getMethod().getMethodName().equalsIgnoreCase(lis.get(j).get("testname"))) {
                    if(lis.get(j).get("execute").equalsIgnoreCase("yes")){
                        methods.get(j).getMethod().setInvocationCount(Integer.parseInt(lis.get(j).get("count")));
                        methods.get(j).getMethod().setPriority(Integer.parseInt(lis.get(j).get("priority")));
                        methods.get(j).getMethod().setDescription(lis.get(j).get("testdescription"));
                        result.add(method);
                    }
                }
            }
        }

        return result;
    }

}
