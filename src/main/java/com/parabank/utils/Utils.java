package com.parabank.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    Properties properties = new Properties();

    public String getUrl() throws IOException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("env.properties");
        properties.load(inputStream);
        return properties.getProperty("url");

    }

    public String getUserName(){
        return properties.getProperty("username");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }
}
