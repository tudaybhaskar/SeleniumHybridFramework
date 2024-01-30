package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.dataObjects.BillingAddress;
import com.app.selenium.dataObjects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CheckoutPage extends BasePage {
    private final By firstNameTxtBox = By.cssSelector("#billing_first_name");
    private final By lastNameTxtBox = By.cssSelector("#billing_last_name");
    private final By cityTxtBox = By.cssSelector("input[id='billing_city']");
    private final By zipCodeTxtBox = By.cssSelector("input[id='billing_postcode']");
    private final By emailTxtBox = By.cssSelector("input[id='billing_email']");
    private final By placeOrderButton = By.cssSelector("button[id='place_order']");
    private final By orderNoticeMessage = By.cssSelector("p.woocommerce-notice--success");
    private final By userNameTxtBox = By.id("username");
    private final By passwordTxtBox = By.id("password");
    private final  By loginButton = By.cssSelector("button[value='Login']");
    private final By billingCountry_dropdown = By.cssSelector("#billing_country");
    private final By billingState_dropdown = By.cssSelector("select#billing_state");
    private final By loginHere_Link = By.cssSelector("a.showlogin");

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameTxtBox).clear();
        driver.findElement(firstNameTxtBox).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameTxtBox).clear();
        driver.findElement(lastNameTxtBox).sendKeys(lastName);
        return this;
    }

    private By getAddressLine1Element(){
        //input[placeholder='House number and street name'][id='billing_address_1']
        return By.cssSelector("input[id='billing_address_1']");
    }

    public CheckoutPage enterAddressLine1(String HouseOrStreet){
        By addressLine1 = getAddressLine1Element();
        driver.findElement(addressLine1).clear();
        driver.findElement(addressLine1).sendKeys(HouseOrStreet);
        return this;
    }

    public CheckoutPage enterCity(String city){
        driver.findElement(cityTxtBox).clear();
        driver.findElement(cityTxtBox).sendKeys(city);
        return this;
    }

    public CheckoutPage enterZipCode(String zipCode){
        driver.findElement(zipCodeTxtBox).clear();
        driver.findElement(zipCodeTxtBox).sendKeys(zipCode);
        waitForOverlaysToDisappear(overlay);
        return this;
    }

    public CheckoutPage enterEmail(String emailAddress){
        driver.findElement(emailTxtBox).clear();
        driver.findElement(emailTxtBox).sendKeys(emailAddress);
        return this;
    }

    public CheckoutPage clickPlaceOrder(){
        //waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderButton).click();
        //waitForOverlaysToDisappear(overlay);
        return this;
    }

    public String getOrderNotice(){
        return driver.findElement(orderNoticeMessage).getText();
    }

    public CheckoutPage enterUserName(String userName){
        driver.findElement(userNameTxtBox).clear();
        driver.findElement(userNameTxtBox).sendKeys(userName);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        driver.findElement(passwordTxtBox).clear();
        driver.findElement(passwordTxtBox).sendKeys(password);
        return this;
    }

    public CheckoutPage clickHereToLogin(){
        driver.findElement(loginHere_Link).click();
        return this;
    }
    /*
    To avoid StaleElementReferenceException, as DOM gets refreshed after clicking on LoginBtn and LoginBtn is removed
    from the DOM . So we are using this LoginBtn to disappear as one of the ways to avoid StaleElementReference exception

    Once this is resolved , we can continue on performing actions on other elements.
     */
    private CheckoutPage waitForLoginBtnToDisappear(){
        waitFor20Seconds.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        driver.findElement(loginButton).click();
        return this;
    }

    public CheckoutPage loginAtCheckoutPage(User user){
        return enterUserName(user.getUserName()).enterPassword(user.getPassword())
                .clickLoginBtn().waitForLoginBtnToDisappear();

    }

    private By getDropDownOption(String option){
        return By.xpath("//ul/li[text()='"+ option +"']");
    }

    public CheckoutPage selectBillingCountry(String selectCountry){
        waitFor5Seconds.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("#select2-billing_country-container"))).click();
        waitFor5Seconds.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field"))).clear();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(selectCountry);
        waitFor5Seconds
                .until(ExpectedConditions.visibilityOfElementLocated(this.getDropDownOption(selectCountry)));
        WebElement country = driver.findElement(this.getDropDownOption(selectCountry));
        country.click();
        // selectByVisibleText(billingCountry_dropdown, selectCountry);
        waitForOverlaysToDisappear(overlay);
        return this;
    }

    public CheckoutPage selectBillingState(String selectState){
        //#select2-billing_state-container
        waitFor5Seconds.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("#select2-billing_state-container"))).click();
        waitFor5Seconds.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field"))).clear();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(selectState);
        waitFor5Seconds
                .until(ExpectedConditions.visibilityOfElementLocated(this.getDropDownOption(selectState)));
        WebElement state = driver.findElement(this.getDropDownOption(selectState));
        state.click();
        /*
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", state );
        javascriptExecutor.executeScript("arguments[0].click();", state );

         */
        //selectByVisibleText(billingState_dropdown, selectState);
        waitForOverlaysToDisappear(overlay);
        return this;
    }

    public void setBillingAddress(BillingAddress billingAddress){
        enterFirstName(billingAddress.getFirstName());
        enterLastName(billingAddress.getLastName());
        selectBillingCountry(billingAddress.getCountry());
        enterAddressLine1(billingAddress.getAddressLine1());
        enterCity(billingAddress.getCity());
        selectBillingState(billingAddress.getState());
        enterZipCode(billingAddress.getZipCode());
        enterEmail(billingAddress.getEmail());

    }

    public CheckoutPage setBillingAddressUsingBuilderPatter(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectBillingCountry(billingAddress.getCountry())
                .enterAddressLine1(billingAddress.getAddressLine1())
                .enterCity(billingAddress.getCity())
                .selectBillingState(billingAddress.getState())
                .enterZipCode(billingAddress.getZipCode())
                .enterEmail(billingAddress.getEmail());
    }

    public String getBillingEmailAddress(){
        return driver.findElement(emailTxtBox).getAttribute("value");
    }

    public CheckoutPage assertOrderIsPlaced(){
        Assert.assertEquals(this.getOrderNotice().trim(), "Thank you. Your order has been received.");
        return this;
    }


}
