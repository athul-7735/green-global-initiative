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
 * This class represents Test Case 9 in Quality Plus in JIRA, using TestNG framework.
 * It navigates to the NGGI Application and verifies the "About Us" page functionality.
 * @author John K
 */
public class AutoTestCase9 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void verifyRedirectionToNGGIApplicationPage() {
        driver.get("https://www.nausicaaglobalgreen.live");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        Assert.assertTrue(pageTitle.contains("GreenGlobalInitiative"), 
                "Step 1 Failed: Redirection to NGGI Application Page failed.");
        System.out.println("Step 1 Passed: Redirected to NGGI Application Page.");
    }

    @Test(priority = 2)
    public void verifyAboutUsButtonAndClick() {
        WebElement aboutButton = driver.findElement(By.linkText("About"));
        Assert.assertTrue(aboutButton.isDisplayed(), 
                "Step 2 Failed: \"About Us\" button not found.");
        System.out.println("Step 2 Passed: \"About Us\" button is present.");

        aboutButton.click();
        System.out.println("Step 2 Passed: The user is able to click on \"About Us\" Button from the header section of the NGGI Application Page.");
    }

    @Test(priority = 3)
    public void verifyNavigationToAboutUsPage() {
        WebElement aboutUsText = driver.findElement(By.xpath("//app-aboutus/div/h1"));
        Assert.assertTrue(aboutUsText.isDisplayed(), 
                "Step 3 Failed: The user is Not Navigated to the \"About Us\" Page post click on the \"About Us\" Button.");
        System.out.println("Step 3 Passed: The user is Navigated to the \"About Us\" Page post click on the \"About Us\" Button.");
    }

    @Test(priority = 4)
    public void validateAboutUsPageSections() {
        int counter = 0; // Initialize the counter

        // Verify each header element and increment the counter if found
        counter += verifyElementPresence(By.xpath("//h5[contains(.,'MISSION ON')]"), "MISSION");
        counter += verifyElementPresence(By.xpath("//h5[contains(.,'VISION ON')]"), "VISION");
        counter += verifyElementPresence(By.xpath("//h5[contains(.,'ACHIEVEMENTS')]"), "ACHIEVEMENTS");
        counter += verifyElementPresence(By.xpath("//h5[contains(.,'CORE VALUES')]"), "CORE VALUES");
       
        // Adding a hard wait of 5 seconds
        try {
            Thread.sleep(5000);  // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check if all 3 elements are verified
        if (counter == 4) {
            System.out.println("Step 3 Passed: The page displays \"Mission\", \"Vision\", \"Core Values\", Achievement\" Section.");
        } else {
            Assert.fail("Step 3 Failed: The page does not displays " + counter + " Section" );
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
