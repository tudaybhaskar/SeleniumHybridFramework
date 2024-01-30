package com.app.selenium.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
        /*
        getClass().getClassLoader().getResourceAsStream("products.json") ;
        I had a doubt that how file path is resolved and could be fetch Products.json file.But the the methods specified
        will help Maven to fetch the file.

        As we made deserializeJson as static , so we need JacksonUtils.class  as we are using inside the JacksonUtils
        class.
         */
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, T);
        /*
        readValue of Type T. For example to Products.json it reads and give value of type Product.
        So, return value is entire product.
         */
    }

}
