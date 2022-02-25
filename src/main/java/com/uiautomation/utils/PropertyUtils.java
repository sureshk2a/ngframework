package com.uiautomation.utils;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.enums.ConfigProperties;
import com.uiautomation.exceptions.PropertyFileUsageExceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public static String get(ConfigProperties key) {

        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))){
            throw new PropertyFileUsageExceptions("The property "+key+" is not found, please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
       
    }


}
