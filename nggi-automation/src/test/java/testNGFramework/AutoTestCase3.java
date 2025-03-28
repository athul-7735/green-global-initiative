package testNGFramework;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This class represents Test Case 3 in Quality Plus in JIRA, using TestNG framework.
 * It navigates to the NGGI Application and Verifies that users can Navigate to All Grants Details Page.
 * @author John K
 */

public class AutoTestCase3 {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void verifyRedirectionToApplicationPage() {
        driver.get("http://localhost:4200/");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("GreenGlobalInitiative"), "Page title does not match");
        System.out.println("Step 1 Passed: Redirected to NGGI Application Page.");
    }

    @Test(priority = 2)
    public void verifySignInSignUpButtonPresence() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        Assert.assertTrue(signInButton.isDisplayed(), "SignIn/SignUp button is not displayed.");
        System.out.println("Prerequisite Step 1 Passed: SignIn/SignUp button is present.");
    }

    @Test(priority = 3)
    public void verifyNavigationToLoginPage() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        signInButton.click();

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed.");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed.");
        Assert.assertTrue(submitButton.isDisplayed(), "Submit button is not displayed.");
        System.out.println("Prerequisite Step 2 Passed: Navigated to Login Page.");
    }

    @Test(priority = 4)
    public void verifyLoginToApplication() {
        inputValues(By.id("email"), "Email", "JamesBond@gmail.com");
        inputValues(By.id("password"), "Password", "JamesBond@12345");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        
        // Adding a hard wait of 5 seconds
        try {
            Thread.sleep(5000);  // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement toastMessage = driver.findElement(By.id("toast-container"));
        Assert.assertTrue(toastMessage.isDisplayed(), "Login toast message is not displayed.");
        String alertText = toastMessage.getText();
        System.out.println("Alert message: " + alertText);
        Assert.assertTrue(alertText.contains("Login Successfull"), "Login was not successful.");
        System.out.println("Prerequisite Step 3 Passed: Logged in successfully.");
    }

    @Test(priority = 5)
    public void verifyHeaderSection() {
        int counter = 0; // Initialize the counter
        // Verify each header element and increment the counter if found
        counter += verifyElementPresence(By.linkText("Home"), "Home");
        counter += verifyElementPresence(By.linkText("About"), "About");
        counter += verifyElementPresence(By.linkText("Grants"), "Grants");
        counter += verifyElementPresence(By.linkText("Grant Form"), "Grant Form");
        counter += verifyElementPresence(By.linkText("My Profile"), "My Profile");
        counter += verifyElementPresence(By.linkText("Log Out"), "Log Out");
        // Check if all 3 elements are verified
        if (counter == 6) {
            System.out.println("Step 2 Passed: Header section is verified.");
        } else {
            Assert.fail("Step 2 Failed: Not all header elements are verified. Verified count: " + counter);
        }
    }


    @Test(priority = 6)
    public void verifyViewAllGrantsButton() {
        verifyElementPresence(By.xpath("//button[contains(.,'View All Grants')]"), "View All Grants");
        System.out.println("Step 3 Passed: 'View All Grants' button is present.");
    }

    @Test(priority = 7)
    public void verifyNavigationToAllGrantsDetailsPage() {
        WebElement viewAllGrantsButton = driver.findElement(By.xpath("//button[contains(.,'View All Grants')]"));
        viewAllGrantsButton.click();

        WebElement grantsTitle = driver.findElement(By.xpath("//h3[contains(.,'Our Grants')]"));
        Assert.assertTrue(grantsTitle.isDisplayed(), "User is not navigated to the 'All Grants Details' page.");
        System.out.println("Step 4 Passed: Navigated to 'All Grants Details' page.");
    }

    @Test(priority = 8)
    public void verifyAllGrantsDetailsPageContent() {
        verifyElementPresence(By.xpath("//h2[contains(.,'Teto Grant')]"), "Teto Grant");
        verifyElementPresence(By.xpath("//h2[contains(.,'Pejite Innovation Grant')]"), "Pejite Innovation Grant");
        verifyElementPresence(By.xpath("//h2[contains(.,'Ohmu Biodiversity Grant')]"), "Ohmu Biodiversity Grant");
        System.out.println("Step 5 Passed: 'All Grants Details' page content is verified.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private int verifyElementPresence(By locator, String elementName) {
        try {
            // Locate the element
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), elementName + " is not displayed."); // Assert it's displayed
            System.out.println(elementName + " is displayed."); // Log success
            return 1; // Return 1 on success
        } catch (NoSuchElementException e) {
            System.err.println(elementName + " is not displayed."); // Log failure
            return 0; // Return 0 on failure
        }
    }


    private void inputValues(By locator, String elementName, String value) {
        try {
            WebElement element = driver.findElement(locator);
            element.sendKeys(value);
            System.out.println(elementName + " value '" + value + "' is inputted.");
        } catch (NoSuchElementException e) {
            Assert.fail(elementName + " value '" + value + "' is not inputted.");
        }
    }
}
