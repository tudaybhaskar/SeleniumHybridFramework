package com.app.selenium.factory.abstractFactory;

import com.app.selenium.constants.DriverType;
import com.app.selenium.factory.ChromeDriverManager;
import com.app.selenium.factory.DriverManagerFactory;
import com.app.selenium.factory.FirefoxDriverManager;

public class AbstractDriverManagerFactory {

    public static AbstractDriverManager getDriverManager(DriverType driverType){

        switch (driverType){
            case CHROME : {
                return new AbstractChromeDriverManager();
            }
            case FIREFOX : {
                return  new AbstractFirefoxDriverManager();
            }
            default : {
                throw new RuntimeException("UnexpectedValue : " + driverType);
            }
        }
    }
}
