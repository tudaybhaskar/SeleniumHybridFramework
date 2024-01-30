package com.app.selenium.pages.components;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.pages.AccountPage;
import com.app.selenium.pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyHeader extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227>a");//#menu-item-1227 > a :this is also accepted
    private final By accountMenuLink = By.cssSelector("#menu-item-1237>a");
    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }

    public AccountPage navigateToAccountUsingMenu(){
        driver.findElement(accountMenuLink).click();
        return new AccountPage(driver);
    }
}
