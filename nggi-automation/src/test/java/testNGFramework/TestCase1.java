package testNGFramework;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents the Test Case 1 in Quality Plus in JIRA.
 * @author Sherine A
 * @author John K
 */

public class TestCase1 {

	/**
     * This method initializes the Edge driver, navigates to NGGI Application, and Navigates to Create an Account Page.
     * @param args Command line arguments (not used in this method).
     */
	
	public static void main(String[] args) { 
		
		
		System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe"); //this line refers to the driver in the Driver Folder under this project
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    
	    // Step 1: Verify redirection to NGGI Application Page
	    driver.get("http://localhost:4200/");
	    
	    String pageTitle = driver.getTitle();
	    System.out.println("Page Title: " + pageTitle); // To get Page Title

	    if (driver.getTitle().contains("GreenGlobalInitiative")) {
            System.out.println(" Step 1 Passed: Redirected to NGGI Application Page.");
        } else {
            System.out.println(" Step 1 Failed: Redirection to NGGI Application Page failed.");
        }
	 
	    
        // Step 2: Verify SignIn/SignUp button is present
	    WebElement SignInButton = driver.findElement(By.linkText("SignIn/SignUp"));
	    boolean isSignInButtonDisplayed = SignInButton.isDisplayed();
	    
	    if (isSignInButtonDisplayed == true)
	    {	    
		    System.out.println(" Step 2 Passed: SignIn/SignUp button is present.");
	    }
	    else
	    {
	    	System.out.println(" Step 2 Failed: SignIn/SignUp button not found");
	    }
	    
	    // Step 3: Verify navigation to Login Page on clicking SignIn/SignUp button
	    if (isSignInButtonDisplayed == true)
	    {		   
	    	SignInButton.click();
	    	WebElement SignIn_EmailButton = driver.findElement(By.id("email"));
	  	    WebElement SignIn_PasswordButton = driver.findElement(By.id("password"));
	  	    WebElement SignIn_SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	  	    
	  	    boolean isSignIn_EmailButton = SignIn_EmailButton.isDisplayed();
	  	    boolean isSignIn_PasswordButton = SignIn_PasswordButton.isDisplayed();
	  	    boolean isSignIn_SubmitButton = SignIn_SubmitButton.isDisplayed();
	  	    
	  	    if (isSignIn_EmailButton && isSignIn_PasswordButton && isSignIn_SubmitButton == true)
	  	    {
	  	    	System.out.println(" Step 3 Passed: The user is able to navigate to the Login Page on clicking Sign-In/Sign-Up Button in the NGGI Application Page.");
	  	    }
	  	    else
	  	    {
	  	    	System.out.println(" Step 3 Failed: The user is Not Able to navigate to the Login Page on clicking Sign-In/Sign-Up Button in the NGGI Application Page.");
	  	    }
	    } 
	    else
	  	{
	    	System.out.println(" Step 3 Failed: SignIn/SignUp Button Not Found & Not Clicked");
	  	}
	  	  
	    // Step 4: Verify Sign-Up button is present on the Login Page
	    WebElement SignIn_SignUpHereButton = driver.findElement(By.xpath("//u[contains(.,'here')]"));
	    boolean isSignIn_SignUpHereButton = SignIn_SignUpHereButton.isDisplayed();
	    
	    if (isSignIn_SignUpHereButton == true)
	    {
	    	System.out.println(" Step 4 Passed: Sign-up Button is Present in the Login Page of NGGI Application.");
	    }
	    else
	    {
	    	System.out.println(" Step 4 Failed: Sign-up Button is Not Present in the Login Page of NGGI Application");
	    }
	    
        // Step 5: Verify navigation to "Create an Account" Page
	    SignIn_SignUpHereButton.click();
	    
	    WebElement CreateAccount_Element = driver.findElement(By.xpath("//h2[contains(.,'Create an account')]"));
	    boolean isCreateAccount_Element = CreateAccount_Element.isDisplayed();
	    
	    if (isCreateAccount_Element == true)
	    {
	    	System.out.println(" Step 5 Passed: The User is able to navigate to \"Create an Account\" Page on clicking the Sign-Up Button in the Login Page.");
	    }
	    else
	    {
	    	System.out.println(" Step 5 Failed: The User is Not able to navigate to \"Create an Account\" Page on clicking the Sign-Up Button in the Login Page.");
	    }
	    
	 // Step 6: Verify fields and buttons on "Create an Account" Page
        verifyElementPresence(driver, By.id("firstName"), "First Name");
        verifyElementPresence(driver, By.id("lastName"), "Last Name");
        verifyElementPresence(driver, By.id("email"), "Email Address");
        verifyElementPresence(driver, By.id("password"), "Password");
        verifyElementPresence(driver, By.id("confirmPassword"), "Confirm Password");
        verifyElementPresence(driver, By.cssSelector(".password-group > .form-group:nth-child(1)"), " Show Password");
        verifyElementPresence(driver, By.xpath("//h2[contains(.,'Create an account')]"), "Create an account");
        
	    driver.quit();
	}

    private static void verifyElementPresence(EdgeDriver driver, By locator, String elementName) {
        try 
        {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) 
            {
                System.out.println(elementName + " is displayed.");
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println(elementName + " is not displayed.");
        }
    }
}
	 
//label[contains(.,' Show Password')]



	  /*  
	    if (isSignIn_EmailButton == true) {
		    SignIn_EmailButton.sendKeys("antonin@gmail.com");
		    System.out.println(" Email Id is displayed & value is inputted");
	    } else {
	    	System.out.println(" Email Id Field is not displayed & value is inputted");
	    }
	    
	    if (isSignIn_PasswordButton == true) {
		    SignIn_PasswordButton.sendKeys("antony@12345");
		    System.out.println(" Password Field is displayed & value is inputted");
	    } else {
	    	System.out.println(" Password Field is not displayed & value is inputted");
	    }
	 
	    if (isSignIn_SubmitButton == true) {
	    	SignIn_SubmitButton.click();
	    	System.out.println("Submit Button is Displayed & Clicked.");
	    } else {
	    	System.out.println("Submit Button is Not Displayed & Not Clicked.");
	    }
	    
	    WebElement SignIn_Message = driver.findElement(By.id("toast-container"));
	    boolean isSignIn_Message = SignIn_Message.isDisplayed();
	   
	    String SignIn_alertText = SignIn_Message.getText();
	    System.out.println("Alert message: " + SignIn_alertText);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    if (isSignIn_Message == true) {
	    	System.out.println("Message is Displayed");
	    	// Check if the alert message contains "Login successful"
	        if (SignIn_alertText.contains("Login successful")) {
	            System.out.println("Login Sucessfull Message Is Displayed");
	            SignIn_Message.click();
	        } 
	        else {
	            System.out.println("Login Sucessfull Message Is Not Displayed");
	            // You might want to add additional actions here, like taking a screenshot
	        }
	    } 
	    else {
	    	System.out.println("Message is Not Displayed");
	    }
	    */








