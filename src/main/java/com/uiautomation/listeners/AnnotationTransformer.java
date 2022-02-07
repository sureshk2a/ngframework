package com.uiautomation.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        Map<String,Object> test1 = new HashMap<>();
        test1.put("name","test1");
        test1.put("count","5");
        test1.put("enabled",true);

        Map<String,Object> test2 = new HashMap<>();
        test2.put("name","test2");
        test2.put("count","2");
        test2.put("enabled",false);

        List<Map<String,Object>> lis = new ArrayList<>();
        lis.add(test1);
        lis.add(test2);

        for(int i=0;i<lis.size();i++){
            if(testMethod.getName().equalsIgnoreCase((String) lis.get(i).get("name"))){
                if(lis.get(i).get("enabled").equals(false)){
                    annotation.setEnabled(false);
                }else {
                    annotation.setInvocationCount(Integer.parseInt((String) lis.get(i).get("count")));
                }
            }
        }

    }



}
