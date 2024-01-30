package com.app.selenium.utils;

import com.app.selenium.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;
    private static final String baseUrl = "https://askomdch.com";

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE :
                properties = PropertyUtils.propertyLoader("src/test/resources/config/stage_config.properties");
                System.out.println("Under Stage section");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertyLoader("src/test/resources/config/production_config.properties");
                break;
            default: throw new IllegalStateException("Invalid env Type: " +env);
        }

    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return  configLoader;
    }

    public String openBaseUrl(){
        return properties.getProperty("baseUrl");
    }




}
