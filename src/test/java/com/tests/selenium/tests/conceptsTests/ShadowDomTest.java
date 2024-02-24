package com.tests.selenium.tests.conceptsTests;

import com.tests.selenium.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShadowDomTest extends BaseTest {


    @Test
    public void verify_AccessShadowDOM(){

        getDriver();
        getDriver().get("file:///Users/udayabhaskar/Documents/Learning/SDET/wdioLearning/webdriverio-typescript-mocha-allure/index.html");

        WebElement element_in_shadowRoot = getDriver().findElement(By.cssSelector("my-custom-element")).getShadowRoot()
                .findElement(By.cssSelector("#shadowButton"));
        String actualText = element_in_shadowRoot.getText();
        System.out.println("Actual text : " + actualText);
        Assert.assertEquals(actualText, "Click Me");
        element_in_shadowRoot.click();
    }
}
