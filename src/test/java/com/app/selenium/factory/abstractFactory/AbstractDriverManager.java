package com.app.selenium.factory.abstractFactory;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverManager {

    protected WebDriver driver;

    public abstract void startDriver();

    public WebDriver getDriver(){
        if(driver == null){
            startDriver();
        }
        return driver;
    }

    public void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver = null ;
        }
    }
}
