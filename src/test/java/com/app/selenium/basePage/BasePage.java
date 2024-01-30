package com.app.selenium.basePage;

import com.app.selenium.pages.CartPage;
import com.app.selenium.pages.FeaturedProductPage;
import com.app.selenium.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait waitFor5Seconds;
    protected WebDriverWait waitFor10Seconds;
    protected WebDriverWait waitFor20Seconds;
    protected JavascriptExecutor javascriptExecutor;


    public BasePage(WebDriver driver){
        this.driver = driver;
        waitFor5Seconds = new WebDriverWait(driver,ofSeconds(5));
        waitFor10Seconds = new WebDriverWait(driver, ofSeconds(10));
        waitFor20Seconds = new WebDriverWait(driver, ofSeconds(20));
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void load(String endpoint){

        driver.get(ConfigLoader.getInstance().openBaseUrl() + endpoint);
    }
    private WebElement productToSelect(String product){
        return driver.findElement(By.cssSelector(".astra-shop-thumbnail-wrap a[href*="+product+"]"));
    }

    public FeaturedProductPage clickOnProduct(String product){
        if(product.contains("Blue Shoes")){
            product = "blue-shoes";
        }
        productToSelect(product).click();
        return new FeaturedProductPage(driver);
    }

    public BasePage addToCart(){
        driver.findElement(By.cssSelector("button.single_add_to_cart_button")).click();
        return this;
    }

    public CartPage viewCart(){
        driver.findElement(By.cssSelector("#ast-desktop-header a.cart-container")).click();
        return new CartPage(driver);
    }

    public BasePage waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        if(!overlays.isEmpty()){
            waitFor10Seconds.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
        }
        return this;
    }

    public void selectByVisibleText(By element, String visibleText){
        waitFor10Seconds.until(ExpectedConditions.elementToBeSelected(element));
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(visibleText);
    }
}
