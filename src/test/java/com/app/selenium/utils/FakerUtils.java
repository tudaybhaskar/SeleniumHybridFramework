package com.app.selenium.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class FakerUtils {

    public Long generateRandomNumber(){
        Faker faker = new Faker();
        return faker.number().randomNumber(3, true);
    }

    public Map<String,String> generateUser(){
        Map<String, String> userMap = new HashMap<>();
        String userName = "testSelenium" + generateRandomNumber();
        String password = "testSelenium" + generateRandomNumber();
        String email = userName + "@testmail.com";
        userMap.put("username", userName);
        userMap.put("password", password);
        userMap.put("email", email);
        return userMap;
    }
    /*
    {
  "firstName" : "DemoFirstName",
  "lastName" : "DemoLastName",
  "addressLine1" : "Sankar Residency",
  "city" : "Chennai",
  "zipCode" : "600098",
  "email" : "testmail@test.com",
  "state" : "Tamil Nadu",
  "country" : "India"
}
     */

    public Map<String, String> generateGuestUser(){
        Map<String, String> userMap = new HashMap<>();
        String userName = "testSelenium" + generateRandomNumber();
        String lastname = "testSelenium" + generateRandomNumber();
        String email = userName + "@testmail.com";
        userMap.put("firstName", userName);
        userMap.put("lastName", lastname);
        userMap.put("email", email);
        /*
        userMap.put("addressLine1", "Sankar Residency");

        userMap.put("zipCode", "600098");
        userMap.put("state", "Tamil Nadu");
        userMap.put("country", "India");
        userMap.put("city", "Chennai");

         */
        return userMap;
    }



}
