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
 * This class represents Test Case 4 in Quality Plus in JIRA, using TestNG framework.
 * It navigates to the NGGI Application and verifies that users can navigate to the Grant Application Form Page.
 * @author John K
 */
public class AutoTestCase4 {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.nausicaaglobalgreen.live");
    }

    @Test(priority = 1)
    public void verifyRedirectionToNGGIApplication() {
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("GreenGlobalInitiative"),
                "Step 1 Failed: Redirection to NGGI Application Page failed.");
        System.out.println("Step 1 Passed: Redirected to NGGI Application Page.");
    }

    @Test(priority = 2)
    public void verifySignInSignUpButtonPresence() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        Assert.assertTrue(signInButton.isDisplayed(), "Step 2 Failed: SignIn/SignUp button not found.");
        System.out.println("Step 2 Passed: SignIn/SignUp button is present.");
    }

    @Test(priority = 3, dependsOnMethods = "verifySignInSignUpButtonPresence")
    public void verifyNavigationToLoginPage() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        signInButton.click();

        boolean emailFieldDisplayed = driver.findElement(By.id("email")).isDisplayed();
        boolean passwordFieldDisplayed = driver.findElement(By.id("password")).isDisplayed();
        boolean submitButtonDisplayed = driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed();

        Assert.assertTrue(emailFieldDisplayed && passwordFieldDisplayed && submitButtonDisplayed,
                "Step 3 Failed: Navigation to Login Page failed.");
        System.out.println("Step 3 Passed: The user is able to navigate to the Login Page.");
    }

    @Test(priority = 4, dependsOnMethods = "verifyNavigationToLoginPage")
    public void verifyLoginFunctionality() {
    	
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        
    	// Verify Email field is displayed
        Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
        System.out.println("Step 4 Passed: Email field is displayed.");

        // Input Email
        emailField.sendKeys("JamesBond@gmail.com");
        System.out.println("Step 4 Passed: Email Id has been Inputted.");

        // Verify Password field is displayed
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
        System.out.println("Step 4 Passed: Password field is displayed.");

        // Input Password
        passwordField.sendKeys("JamesBond@12345");
        System.out.println("Step 4 Passed: Password has been Inputted.");
    	

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(submitButton.isDisplayed(), "Step 5 Failed: Sign In button not displayed.");
        submitButton.click();
        System.out.println("Step 5 Passed: The user is able to click on the Sign In button.");
    }

    @Test(priority = 5, dependsOnMethods = "verifyLoginFunctionality")
    public void verifySuccessfulLoginNavigation() throws InterruptedException {
        Thread.sleep(5000); // Wait for potential toast message
        WebElement alertMessage = driver.findElement(By.id("toast-container"));
        Assert.assertTrue(alertMessage.isDisplayed(), "Step 6 Failed: Alert message not displayed.");

        String alertText = alertMessage.getText();
        Assert.assertTrue(alertText.contains("Login Successfull"), "Step 6 Failed: Login successful message not displayed.");
        alertMessage.click();
        System.out.println("Step 6 Passed: User navigated back to the home page upon successful login.");
    }

    @Test(priority = 6, dependsOnMethods = "verifySuccessfulLoginNavigation")
    public void verifyHeaderSectionButtons() {
        int counter = 0; // Initialize the counter

        // Verify each header button and increment the counter if found
        counter += verifyElementPresence(By.linkText("Home"), "Home");
        counter += verifyElementPresence(By.linkText("About"), "About");
        counter += verifyElementPresence(By.linkText("Grants"), "Grants");
        counter += verifyElementPresence(By.linkText("Grant Form"), "Grant Form");
        counter += verifyElementPresence(By.linkText("My Profile"), "My Profile");
        counter += verifyElementPresence(By.linkText("Log Out"), "Log Out");

        // Check if all 6 buttons are verified
        if (counter == 6) {
            System.out.println("Step 7 Passed: Header section contains all expected buttons.");
        } else {
            Assert.fail("Step 7 Failed: Header section is missing some buttons. Verified " + counter + " out of 6 buttons.");
        }
    }

    @Test(priority = 7, dependsOnMethods = "verifyHeaderSectionButtons")
    public void verifyGrantFormNavigation() {
        WebElement grantFormButton = driver.findElement(By.linkText("Grant Form"));
        Assert.assertTrue(grantFormButton.isDisplayed(), "Step 8 Failed: Grant Form button not Displayed.");
        grantFormButton.click();
        System.out.println("Step 8 Passed: Grant Form button is Displayed & Clicked");

        WebElement applicationFormElement = driver.findElement(By.xpath("//app-grant-application/div/div"));
        Assert.assertTrue(applicationFormElement.isDisplayed(), "Step 9 Failed: Grant Application Form not displayed.");
        Assert.assertTrue(applicationFormElement.getText().contains("Application Form"),
                "Step 9 Failed: Incorrect text on Grant Application Form.");
        System.out.println("Step 9 Passed: User navigated to the Grant Application Form Page.");
    }

    @Test(priority = 8, dependsOnMethods = "verifyGrantFormNavigation")
    public void verifyGrantApplicationFormFields() throws InterruptedException {
        int counter = 0; // Initialize the counter
        Thread.sleep(5000);
        // Verify each field element and increment the counter if found
        counter += verifyElementPresence(By.id("organizationName"), "Organization Name");
        counter += verifyElementPresence(By.id("grantName"), "Grant Name");
        counter += verifyElementPresence(By.id("budget"), "Budget");
        counter += verifyElementPresence(By.id("projectDescription"), "Project Description");
        counter += verifyElementPresence(By.xpath("//button[contains(.,'Submit Application')]"), "Submit Application");

        // Check if all 5 elements are verified
        if (counter == 5) {
            System.out.println("Step 10 Passed: Grant Application Form contains all required fields.");
        } else {
            Assert.fail("Step 10 Failed: Grant Application Form is missing some fields. Verified " + counter + " out of 5 fields.");
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


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
