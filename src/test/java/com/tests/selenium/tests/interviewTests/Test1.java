package com.tests.selenium.tests.interviewTests;

import com.app.selenium.utils.ConfigLoader;
import com.tests.selenium.base.BaseTest;
import org.apache.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Test1 extends BaseTest {

    @Test
    public void verifyBrokenLinks() {
        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("a")));
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        System.out.println("element links size : " + links.size());
        int counter = 0;
        for(WebElement element : links){
            System.out.println("Counter : " + counter++);
            String url = element.getAttribute("href");
            verifyLink(url);
        }

    }

    public void verifyLink(String url)  {
        try {
            URL link = new URL(url);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) link.openConnection();
            httpUrlConnection.setConnectTimeout(3000);
            httpUrlConnection.connect();

            if (httpUrlConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpUrlConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " + httpUrlConnection.getResponseMessage() + " - " + "is a broken link");
            }
        }catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }
}
