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
 * TestNG Test Case 6: Verifies Admin users can access the NGGI Application and navigate to the Admin Dashboard Page.
 * Test Case Execution for NGGI.
 * @author John K
 */
public class AutoTestCase6 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:4200/");
    }

    @Test(priority = 1)
    public void verifyRedirectionToApplicationPage() {
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("GreenGlobalInitiative"), 
                "Step 1 Failed: Redirection to NGGI Application Page failed.");
        System.out.println("Step 1 Passed: Redirected to NGGI Application Page.");
    }

    @Test(priority = 2)
    public void verifySignInButtonPresence() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        Assert.assertTrue(signInButton.isDisplayed(), "Step 2 Failed: SignIn/SignUp button is not present.");
        System.out.println("Step 2 Passed: SignIn/SignUp button is present.");
    }

    @Test(priority = 3, dependsOnMethods = "verifySignInButtonPresence")
    public void verifyNavigationToLoginPage() {
        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
        signInButton.click();
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), 
                "Step 3 Failed: Email field is not displayed.");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), 
                "Step 3 Failed: Password field is not displayed.");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(), 
                "Step 3 Failed: Submit button is not displayed.");
        System.out.println("Step 3 Passed: Navigation to Login Page is successful.");
    }

    @Test(priority = 4, dependsOnMethods = "verifyNavigationToLoginPage")
    public void verifyInputLoginDetails() {
        inputValues(By.id("email"), "Email", "Sherineanbaiyan18@gmail.com");
        inputValues(By.id("password"), "Password", "Password@1234");
        System.out.println("Step 4 Passed: Login details inputted successfully.");
    }

    @Test(priority = 5, dependsOnMethods = "verifyInputLoginDetails")
    public void verifySignInFunctionality() {
        WebElement signInSubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(signInSubmitButton.isDisplayed(), "Step 5 Failed: Sign In button not displayed.");
        signInSubmitButton.click();
        System.out.println("Step 5 Passed: Sign In button clicked successfully.");
    }

    @Test(priority = 6, dependsOnMethods = "verifySignInFunctionality")
    public void verifySuccessfulLoginMessage() throws InterruptedException {
        Thread.sleep(5000); // Wait for the toast message
        WebElement signInMessage = driver.findElement(By.id("toast-container"));
        Assert.assertTrue(signInMessage.isDisplayed(), "Step 6 Failed: Login success message is not displayed.");
        Assert.assertTrue(signInMessage.getText().contains("Login Successfull"), 
                "Step 6 Failed: Login success message text mismatch.");
        System.out.println("Step 6 Passed: Login success message is displayed.");
    }

    @Test(priority = 7, dependsOnMethods = "verifySuccessfulLoginMessage")
    public void verifyAdminDashboard() {
        WebElement adminDashboard = driver.findElement(By.xpath("//h1[contains(.,'Application Overview')]"));
        Assert.assertTrue(adminDashboard.isDisplayed(), 
                "Step 7 Failed: Admin Dashboard is not displayed.");
        System.out.println("Step 7 Passed: Admin Dashboard is displayed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    private void inputValues(By locator, String elementName, String value) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), elementName + " field is not displayed.");
            element.sendKeys(value);
            System.out.println(elementName + " value '" + value + "' inputted successfully.");
        } catch (NoSuchElementException e) {
            Assert.fail(elementName + " field not found.");
        }
    }
}
