package com.uiautomation.utils;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public final class PropertyUtils {
    private PropertyUtils(){}

    private static Properties prop = new Properties();
    private static final Map<String,String> CONFIGMAP = new HashMap<>();

    static {
        try {
            FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath());
            prop.load(file);
            prop.entrySet().forEach(entry -> CONFIGMAP.put((String) entry.getKey(),(String) entry.getValue()));

        } catch (IOException e1){
            e1.printStackTrace();
        }

    }

    public static String get(ConfigProperties key) throws Exception {

        if(Objects.isNull(CONFIGMAP.get(key)) || Objects.isNull(CONFIGMAP.get(key))){
            throw new Exception("The property "+key+" is not found, please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());

    }


}
