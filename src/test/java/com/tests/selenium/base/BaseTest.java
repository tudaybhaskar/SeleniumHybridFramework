package com.tests.selenium.base;

import com.app.selenium.constants.DriverType;
import com.app.selenium.factory.abstractFactory.AbstractDriverManager;
import com.app.selenium.factory.abstractFactory.AbstractDriverManagerFactory;
import com.app.selenium.utils.CookieUtils;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<AbstractDriverManager> driverManager = new ThreadLocal<>();
    protected ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
    public void setDriverManager(AbstractDriverManager abstractDriverManager){
        this.driverManager.set(abstractDriverManager);
    }
    public AbstractDriverManager getDriverManager(){
        return this.driverManager.get();
    }

   public void setDriver(WebDriver driver){

        tDriver.set(driver);
   }

   public WebDriver getDriver(){
       return tDriver.get();
   }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional("") String browser){
        if( browser.isEmpty() ){
            browser = "CHROME";
        }
        /*
        We can now execute the tests by passing the chrome browser parameter via command line and also can run the
        tests via testng.xml file as well

        mvn clean test -Dbrowser=CHROME --> via command line

         */
        /*
        @Parameters annotation value takes precedence in testng for executing.
        The browser value specified from testng.xml file is given the first priority.
         */

        //browser = System.getProperty("browser","CHROME");
       /*
       setDriver(DriverManagerFactory.getManager(DriverType.valueOf("chrome")).createDriver());
       setDriverManager(AbstractDriverManagerFactory.getManager(DriverType.valueOf("chrome")));
       when using abstract class for Driver setup
        */
        setDriverManager(AbstractDriverManagerFactory.getDriverManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
    }

    @AfterMethod
    public synchronized void quitDriver(ITestResult iTestResult) throws IOException {
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            File destFile = new File("screenshots" + File.separator + iTestResult.getTestClass()
                    .getRealClass().getSimpleName()+"_"+
                    iTestResult.getMethod().getMethodName()+".png");
            takeScreenshot(destFile);
        }
        if(iTestResult.getStatus() == ITestResult.SUCCESS){
            File destFile = new File("screenshots" + File.separator + iTestResult.getTestClass()
                    .getRealClass().getSimpleName()+"_"+
                    iTestResult.getMethod().getMethodName()+".png");
            takeScreenshot(destFile);
        }

        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    public void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);
    }

}
