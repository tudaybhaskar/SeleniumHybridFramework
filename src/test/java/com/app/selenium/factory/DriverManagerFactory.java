package com.app.selenium.factory;

import com.app.selenium.constants.DriverType;
import org.openqa.selenium.WebDriver;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType){

        switch (driverType){
            case CHROME : {
                return new ChromeDriverManager();
            }
            case FIREFOX : {
                return  new FirefoxDriverManager();
            }
            default : {
                throw new RuntimeException("UnexpectedValue : " + driverType);
            }
        }
    }
}
