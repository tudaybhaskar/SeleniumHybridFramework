package com.app.selenium.factory;

import org.openqa.selenium.WebDriver;

public interface DriverManager {

    WebDriver createDriver();//abstract method - No method body
    /*
    When we decide on a type of entity by its behavior and not via attribute we should define it as an interface.

    So to decide on which browser to launch, based on the value we pass, we can define DriverManager as interface.
    DriverManager.getManager(of type Chrome).createDriver(); - this way we can create driver of type chrome based on passing
    browser type.


     */

}
