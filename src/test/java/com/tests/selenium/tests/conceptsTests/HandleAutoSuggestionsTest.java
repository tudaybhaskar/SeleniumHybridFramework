package com.tests.selenium.tests.conceptsTests;

import com.tests.selenium.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HandleAutoSuggestionsTest extends BaseTest {

    @Test
    public void verify_AutoSuggestionsFromFlipKart() throws InterruptedException {
        /*
        Remove the blur property from Event listeners and then press f8 key to pause the debugger, Now
        we can be able to inspect the auto suggestions using inspect element

        We also have Selectorshub plugin to perform the above operation, In that plugin we have pause debugger option
        as well and this will pause the browser to inspect the auto suggestions.
         */
        WebDriverWait waitFor5Sec = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));

        getDriver().get("https://www.flipkart.com/");
        WebElement input = waitFor5Sec.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search for Products, Brands and More']")));
        input.sendKeys("macbook pro");
        List<WebElement> suggestionList1 = getDriver()
                .findElements(By
                        .cssSelector("li._3D0G9a div[class*='_2VHNef']"));
        System.out.println("Length of elements: " + suggestionList1.size());

        waitForAutoSuggestionsToLoad( By.cssSelector("li._3D0G9a div[class*='_2VHNef']"));
        //Thread.sleep(3000);
        List<WebElement> suggestionList = getDriver()
                .findElements(By
                        .cssSelector("li._3D0G9a div[class*='_2VHNef']"));

        System.out.println("Suggestions are : ");
        for( WebElement element : suggestionList ){

            System.out.println(element.getText());
        }
    }

    private void waitForAutoSuggestionsToLoad(By child){
        WebDriverWait waitFor10Sec = new WebDriverWait(getDriver(), Duration.ofSeconds(10000));

        waitFor10Sec.until(driver -> {
            List<WebElement> suggestions = driver.findElements(child);
            return suggestions.stream().anyMatch(element -> element.getText().contains("macbook pro"));
        });
    }
}
