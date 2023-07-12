package com.sample.test.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    private Configuration config;
    protected WebDriver driver;
    protected String url;

    @BeforeClass(alwaysRun = true)
    public void init() throws Throwable {
        config = new Configuration();
        url = config.getUrl();
        initializeDriver();
        navigateToSite();
    }

    private void navigateToSite() {
        driver.get(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeDriver() {
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported browser: " + config.getBrowser());
        }
    }

    public void openPizzaOrderForm() {
        navigateToSite();
    }

    public void selectPizza(String pizzaId, String pizzaType) {
        WebElement pizzaSelect = driver.findElement(By.id(pizzaId));
        pizzaSelect.sendKeys(pizzaType);
    }

    public void selectToppings(String toppingsXpath, String toppingsOption) {
        WebElement toppingsSelect = driver.findElement(By.xpath(toppingsXpath));
        toppingsSelect.sendKeys(toppingsOption);
    }

    public void enterQuantity(String quantityId, int quantity) {
        WebElement quantityInput = driver.findElement(By.id(quantityId));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }
    
    public void enterName(String nameId, String name) {
        WebElement nameElement = driver.findElement(By.id(nameId));
        nameElement.clear(); // Clear any existing value
        nameElement.sendKeys(name);
    }

    public void enterEmail(String emailId, String email) {
        WebElement emailElement = driver.findElement(By.id(emailId));
        emailElement.clear(); // Clear any existing value
        emailElement.sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumberId, String phone) {
        WebElement phoneElement = driver.findElement(By.id(phoneNumberId));
        phoneElement.clear(); // Clear any existing value
        phoneElement.sendKeys(phone);
    }

    public void rdbbtnCash() {
        WebElement rdbCashElement = driver.findElement(By.id("cashpayment"));
        rdbCashElement.click();
    }
    
    public void rdbbtnCC() {
        WebElement  rdbCCElement = driver.findElement(By.id("ccpayment"));
        rdbCCElement.click();
    }
    
    public void placeOrder() {
        WebElement placeOrderButton = driver.findElement(By.id("placeOrder"));
        placeOrderButton.click();
    }

    public void resetFields() {
        WebElement resetButton = driver.findElement(By.id("reset"));
        resetButton.click();
    }
    
    public void closeDialog() {
//    	driver.switchTo().alert().dismiss();
    	WebElement closeButtonElement = driver.findElement(By.xpath("//div[5]/div[1]/button/span[1]"));
    	closeButtonElement.click();
    }
    
    

    public void close() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
