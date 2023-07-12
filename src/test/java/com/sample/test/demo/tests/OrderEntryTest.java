package com.sample.test.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

public class OrderEntryTest extends TestBase {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Initialize ExtentReports and create a new test report
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/PizzaOrderTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterMethod
    public void tearDown() {
        // Flush the test report
        extent.flush();
    }

    @Test(priority = 1, testName = "positiveTest1")
    public void positiveTest1() {
        test = extent.createTest("Positive Test 1");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings1']", PizzaToppings.OLIVES.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings2']", PizzaToppings.MUSHROOMS.getDisplayName());
        enterQuantity("pizza1Qty", 2);
        enterName("name", "Arasi");
        enterEmail("email", "arasi@gmail.com");
        enterPhoneNumber("phone", "1234567890");
        rdbbtnCC();
        placeOrder();
        String successMessage = getDialogMessage();
        Assert.assertTrue(successMessage.contains("Thank you for your order!"), "Order placement success!!");
        test.log(Status.PASS, "Positive Test 1 Passed");
        closeDialog();
        resetFields();
    }

    @Test(priority = 2, testName = "positiveTest2")
    public void positiveTest2() {
        test = extent.createTest("Positive Test 2");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.SMALL_ONETOPPINGS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings1']", PizzaToppings.PEPPERONI.getDisplayName());
        enterQuantity("pizza1Qty", 1);
        enterName("name", "Boopathy");
        enterEmail("email", "Boopathy@gmail.com");
        enterPhoneNumber("phone", "0123456789");
        rdbbtnCash();
        placeOrder();
        String successMessage = getDialogMessage();
        Assert.assertTrue(successMessage.contains("Thank you for your order!"), "Order placement success!!");
        test.log(Status.PASS, "Positive Test 2 Passed");
        closeDialog();
        resetFields();
    }

    @Test(priority = 4, testName = "negativeTest1")
    public void negativeTest1() {
        test = extent.createTest("Negative Test 1");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.SMALL_NOTOPPINGS.getDisplayName());
        enterQuantity("pizza1Qty", 2);
        enterName("name", "");//passing null value for a mandatory field
        enterEmail("email", "arasi@gmail.com");
        enterPhoneNumber("phone", "1234567890");
        rdbbtnCash();
        placeOrder();
        String errorMessage = getDialogMessage();
        Assert.assertTrue(errorMessage.contains("Missing name"), "Validation error displayed");
        test.log(Status.PASS, "Negative Test 1 Passed");
        closeDialog();
        resetFields(); 
    }

    @Test(priority = 3, testName = "negativeTest2")
    public void negativeTest2() {
        test = extent.createTest("Negative Test 2");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings1']", PizzaToppings.OLIVES.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings2']", PizzaToppings.MUSHROOMS.getDisplayName());
        enterQuantity("pizza1Qty", 2);
        enterName("name", "Arasi");//passing null value for a mandatory field
        enterEmail("email", "arasi@gmail.com");
        enterPhoneNumber("phone", "");
        rdbbtnCash();
        placeOrder();
        String errorMessage = getDialogMessage();
        Assert.assertTrue(errorMessage.contains("Missing phone number"), "Validation error displayed");
        test.log(Status.PASS, "Negative Test 2 Passed");
        closeDialog();
        resetFields(); 
    }
    
   
    @Test(priority = 5, testName = "BoundaryTest1")
    public void boundaryTest1() {
        test = extent.createTest("Boundary Test 1");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings1']", PizzaToppings.MANGOS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings2']", PizzaToppings.OLIVES.getDisplayName());
        enterQuantity("pizza1Qty", 10000); // Maximum high quantity
        enterName("name", "Arasi");
        enterEmail("email", "arasi@gmail.com");
        enterPhoneNumber("phone", "1234567890");
        placeOrder();
        String successMessage = getDialogMessage();
        Assert.assertTrue(successMessage.contains("Thank you for your order!") && successMessage.contains("TOTAL:"), "Order approved for 10000 quantity");
        test.log(Status.PASS, "Boundary Test 1 Passed");
        closeDialog();
        resetFields();
    }

    @Test(priority = 6, testName = "BoundaryTest2")
    public void boundaryTest2() {
        test = extent.createTest("Boundary Test 2");
        openPizzaOrderForm();
        selectPizza("pizza1Pizza", PizzaTypes.LARGE_THREETOPPINGS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings1']", PizzaToppings.MUSHROOMS.getDisplayName());
        selectToppings("//div[@id='pizza1']//select[@class='toppings2']", PizzaToppings.SALAMI.getDisplayName());
        enterQuantity("pizza1Qty", 0); // Zero quantity
        enterName("name", "Boopathy");
        enterEmail("email", "Boopathy@gmail.com");
        enterPhoneNumber("phone", "0123456789");
        placeOrder();
        String errorMessage = getDialogMessage();
        Assert.assertTrue(errorMessage.contains("TOTAL: 0"), "Order approved for 0 quantity");
        test.log(Status.PASS, "Boundary Test 2 Passed");
        closeDialog();
        resetFields();
    }
    @Test(priority = 7, testName = "excludeTest")
    public void excludeTest() {
       
        System.out.println("This Test is under construction and excluded for this test execution");
       
    }

    private String getDialogMessage() {
        WebElement dialogElement = driver.findElement(By.id("dialog"));
        WebElement messageElement = dialogElement.findElement(By.xpath(".//p"));
        return messageElement.getText();
    }
}
