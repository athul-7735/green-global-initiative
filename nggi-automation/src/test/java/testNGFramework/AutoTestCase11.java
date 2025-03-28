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

public class AutoTestCase11 {

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
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Step 3 Failed: Email field not found.");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Step 3 Failed: Password field not found.");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(),
                "Step 3 Failed: Submit button not found.");
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
        verifyElementPresence(By.cssSelector(".password-group > .form-group:nth-child(1)"), "Show Password");
        verifyElementPresence(By.xpath("//h2[contains(.,'Create an account')]"), "Create an account header");
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

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
