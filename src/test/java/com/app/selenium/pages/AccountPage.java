package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.dataObjects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private final By userNameTxtBox = By.cssSelector("#username");
    private final By passwordTxtBox = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("button[value='Log in']");
    private final By userAccountName = By.xpath("(//div[@class='woocommerce-MyAccount-content']//p/strong)[1]");

    public AccountPage(WebDriver driver){
        super(driver);
    }

    public AccountPage enterUserName(String userName){
        driver.findElement(userNameTxtBox).clear();
        driver.findElement(userNameTxtBox).sendKeys(userName);
        return this;
    }

    public AccountPage enterPassword(String password){
        driver.findElement(passwordTxtBox).clear();
        driver.findElement(passwordTxtBox).sendKeys(password);
        return this;
    }

    public AccountPage clickLogin(){
        driver.findElement(loginButton).click();
        return this;
    }

    public AccountPage login(User user){
        return enterUserName(user.getUserName())
                .enterPassword(user.getPassword())
                .clickLogin();
    }

    private String getAccountNameOnAccountPage(){
        return driver.findElement(userAccountName).getText();
    }

    public boolean validateLoginUser(User user){
        return user.getUserName().contentEquals(getAccountNameOnAccountPage());
    }



}
