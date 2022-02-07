package com.uiautomation.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext iTestContext) {
        List<IMethodInstance> result = new ArrayList<IMethodInstance>();

        Map<String,String> test1 = new HashMap<>();
        test1.put("name","test1");
        test1.put("count","5");

//        Map<String,String> test2 = new HashMap<>();
//        test2.put("name","test2");
//        test2.put("count","2");

        List<Map<String,String>> lis = new ArrayList<>();
        lis.add(test1);
        //lis.add(test2);

        for(int i=0;i<methods.size();i++){
            for(int j=0;j<lis.size();j++){
                if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(lis.get(j).get("name"))){
                    methods.get(j).getMethod().setInvocationCount(Integer.parseInt(lis.get(j).get("count")));
                    result.add(methods.get(i));
                }
            }
        }

        return result;
    }

}
