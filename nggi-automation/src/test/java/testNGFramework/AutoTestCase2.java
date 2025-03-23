package testNGFramework;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutoTestCase2 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void verifyRedirectionToApplicationPage() {
        driver.get("http://localhost:4200/");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

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

        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Email field not displayed.");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Password field not displayed.");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(),
                "Submit button not displayed.");
        System.out.println("Step 3 Passed: User navigated to Login Page.");
    }

    @Test(priority = 4, dependsOnMethods = "verifyNavigationToLoginPage")
    public void verifySignUpButtonPresence() {
        WebElement signUpButton = driver.findElement(By.xpath("//u[contains(.,'here')]"));
        Assert.assertTrue(signUpButton.isDisplayed(), "Step 4 Failed: Sign-up button not found on Login Page.");
        System.out.println("Step 4 Passed: Sign-up button is present on Login Page.");
    }

    @Test(priority = 5, dependsOnMethods = "verifySignUpButtonPresence")
    public void verifyNavigationToCreateAccountPage() {
        WebElement signUpButton = driver.findElement(By.xpath("//u[contains(.,'here')]"));
        signUpButton.click();

        WebElement createAccountElement = driver.findElement(By.xpath("//h2[contains(.,'Create an account')]"));
        Assert.assertTrue(createAccountElement.isDisplayed(),
                "Step 5 Failed: Navigation to 'Create an Account' page failed.");
        System.out.println("Step 5 Passed: User navigated to 'Create an Account' page.");
    }

    @Test(priority = 6, dependsOnMethods = "verifyNavigationToCreateAccountPage")
    public void verifyFieldsOnCreateAccountPage() {
        verifyElementPresence(By.id("firstName"), "First Name");
        verifyElementPresence(By.id("lastName"), "Last Name");
        verifyElementPresence(By.id("email"), "Email Address");
        verifyElementPresence(By.id("password"), "Password");
        verifyElementPresence(By.id("confirmPassword"), "Confirm Password");
        verifyElementPresence(By.xpath("//input[@type='checkbox']"), "Show Password");
        verifyElementPresence(By.xpath("//h2[contains(.,'Create an account')]"), "Create an account header");
    }

    @Test(priority = 7, dependsOnMethods = "verifyFieldsOnCreateAccountPage")
    public void verifyUserRegistration() throws InterruptedException {
        InputValues(By.id("firstName"), "First Name", "James");
        InputValues(By.id("lastName"), "Last Name", "Bond");
        InputValues(By.id("email"), "Email Address", "JamesBond@gmail.com");
        InputValues(By.id("password"), "Password", "JamesBond@12345");
        InputValues(By.id("confirmPassword"), "Confirm Password", "JamesBond@12345");

        WebElement showPassword = driver.findElement(By.xpath("//input[@type='checkbox']"));
        showPassword.click();
        System.out.println("Step 8 Passed: Show Password checkbox clicked.");

        WebElement createAccountButton = driver.findElement(By.xpath("//button[@type='submit']"));
        createAccountButton.click();
        System.out.println("Step 8 Passed: Create Account button clicked.");
        TimeUnit.SECONDS.sleep(15);
    }

    @Test(priority = 8, dependsOnMethods = "verifyUserRegistration")
    public void verifyRedirectionToLoginPage() {
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Email field not displayed.");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Password field not displayed.");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(),
                "Submit button not displayed.");
        System.out.println("Step 9 Passed: User redirected back to Login Page.");
    }

    @Test(priority = 9, dependsOnMethods = "verifyRedirectionToLoginPage")
    public void verifyLoginToApplication() {
        InputValues(By.id("email"), "Email", "JamesBond@gmail.com");
        InputValues(By.id("password"), "Password", "JamesBond@12345");

        WebElement signInSubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInSubmitButton.click();

        WebElement signInMessage = driver.findElement(By.id("toast-container"));
        Assert.assertTrue(signInMessage.isDisplayed(), "Login success message not displayed.");

        String alertText = signInMessage.getText();
        System.out.println("Alert message: " + alertText);
        Assert.assertTrue(alertText.contains("Login Successfull"), "Step 9 Failed: Login unsuccessful.");
        System.out.println("Step 9 Passed: User logged in successfully.");
    }

    private void verifyElementPresence(By locator, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), elementName + " is not displayed.");
            System.out.println(elementName + " is displayed. Test Passed.");
        } catch (NoSuchElementException e) {
            Assert.fail(elementName + " is not displayed.");
        }
    }

    private void InputValues(By locator, String elementName, String value) {
        try {
            WebElement element = driver.findElement(locator);
            element.sendKeys(value);
            System.out.println(elementName + " value is inputted as: " + value);
        } catch (NoSuchElementException e) {
            Assert.fail(elementName + " value could not be inputted.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
