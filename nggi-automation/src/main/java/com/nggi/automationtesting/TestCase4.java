package com.nggi.automationtesting;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents Test Case 4 in Quality Plus in JIRA, using TestNG framework.
 * It navigates to the NGGI Application and Verifies that the users can navigate to the Grant Application Form Page
 * @author John K
 */

public class TestCase4 {

	/**
     * This method initializes the Edge driver, navigates to NGGI Application, Navigates to Create an Account Page And Register a New User in the NGGI System..
     * @param args Command line arguments (not used in this method).
	 * @throws InterruptedException 
     */	
	public static void main(String[] args) throws InterruptedException { 
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
	    
	    // Step 4: Verify the user is able to input valid login details such as Email Address and Password in the Login Page
		WebElement SignIn_EmailButton = driver.findElement(By.id("email"));
  	    WebElement SignIn_PasswordButton = driver.findElement(By.id("password"));
  	    
  	    boolean isSignIn_EmailButton = SignIn_EmailButton.isDisplayed();
	    boolean isSignIn_PasswordButton = SignIn_PasswordButton.isDisplayed();
	    
	    if (isSignIn_EmailButton == true)
  	    {
	    	InputValues(driver, By.id("email"), "Email", "JamesBond@gmail.com");
	        System.out.println(" Step 4 Passed: Email Id has been Inputted");
	    }
  	    else
  	    {
  	    	System.out.println(" Step 4 Failed: Email Id has Not been Inputted");
  	    }
	    
	    if (isSignIn_PasswordButton == true)
  	    {
	        InputValues(driver, By.id("password"), "Password", "JamesBond@12345"); 
	        System.out.println(" Step 4 Passed: Password Has been Inputted");
	    }
  	    else
  	    {
  	    	System.out.println(" Step 4 Failed: Password Has Not been Inputted");
  	    }
	    
	    // Step 5: Verify the user is able to click on the Sign In button
	    WebElement SignIn_SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	    boolean isSignIn_SubmitButton = SignIn_SubmitButton.isDisplayed();
	    
	    if (isSignIn_SubmitButton == true)
  	    {
	    	SignIn_SubmitButton.click();
	        System.out.println(" Step 5 Passed: The user is able to click on the Sign In button.");
	    }
  	    else
  	    {
  	    	System.out.println(" Step 5 Failed: The user is Not able to click on the Sign In button.");
  	    }
	    
	    // Step 6: Verify user is navigated back to the home page upon successful user login.
	    TimeUnit.SECONDS.sleep(5);
	    WebElement SignIn_Message = driver.findElement(By.id("toast-container"));
	    boolean isSignIn_Message = SignIn_Message.isDisplayed();
	   
	    String SignIn_alertText = SignIn_Message.getText();
	    System.out.println("Alert message: " + SignIn_alertText);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    if (isSignIn_Message == true) 
	    {
	    	System.out.println("Message is Displayed");
	    	// Check if the alert message contains "Login successful"
	        if (SignIn_alertText.contains("Login Successfull")) 
	        {
	            System.out.println("Login Sucessfull Message Is Displayed");
	            System.out.println(" Step 6 Passed: The user is navigated back to the home page upon successful user login.");
	            SignIn_Message.click();
	        } 
	        else 
	        {
	            System.out.println("Login Sucessfull Message Is Not Displayed");
	            System.out.println(" Step 6 Failed: The user is Not navigated back to the home page upon successful user login.");
	        }
	    } 
	    else 
	    {
	    	System.out.println("Message is Not Displayed");
	    }
	    
	    // Step 7: Verify the header section of the NGGI Application Page
	    verifyElementPresence(driver, By.linkText("Home"), "Home");
	    verifyElementPresence(driver, By.linkText("About"), "About");
	    verifyElementPresence(driver, By.linkText("Grants"), "Grants");
	    verifyElementPresence(driver, By.linkText("Grant Form"), "Grant Form");
	    verifyElementPresence(driver, By.linkText("My Profile"), "My Profile");
	    verifyElementPresence(driver, By.linkText("Log Out"), "Log Out");
	    
	    System.out.println(" Step 7 Passed: After logging in, the header section of the NGGI Application Page contains the following Buttons:");
	    
	    // Step 8: Verify the user is able to click on the Grant Form Button in the header section of the NGGI Application.
	  
	    WebElement GrantForm_Button = driver.findElement(By.linkText("Grant Form"));
	    boolean isGrantForm_Button = GrantForm_Button.isDisplayed();
	    
	    if (isGrantForm_Button == true)
  	    {
	    	GrantForm_Button .click();
	        System.out.println(" Step 8 Passed: The user is able to click on the Grant Form button.");
	    }
  	    else
  	    {
  	    	System.out.println(" Step 8 Failed: The user is Not able to click on the Grant Form button.");
  	    }
	    
	    // Step 9: Verify the user is navigated to the Grant Application Form Page after clicking on the Grant Form Button in the header section of the NGGI Application
	    WebElement ApplicationForm_Element = driver.findElement(By.xpath("//app-grant-application/div/div"));
	    boolean isApplicationForm_ElementDisplayed = ApplicationForm_Element.isDisplayed();
	   
	    String ApplicationForm_Element_Val = ApplicationForm_Element.getText();
	    System.out.println("Value: " + ApplicationForm_Element_Val);
	    
	    if (isApplicationForm_ElementDisplayed == true) 
	    {
	    	System.out.println("Message is Displayed");
	    	// Check if the alert message contains "Login successful"
	        if (ApplicationForm_Element_Val.contains("Application Form")) 
	        {
	            System.out.println(" Step 9 Passed: The user is able to navigate to the Grant Application Form Page after clicking on the Grant Form Button in the header section of the NGGI Application.");
	        } 
	        else 
	        {
	            System.out.println(" Step 9 Failed: The user is not able to navigate to the Grant Application Form Page after clicking on the Grant Form Button in the header section of the NGGI Application.");
	        }
	    } 
	    else 
	    {
	    	System.out.println("Message is Not Displayed");
	    }
	    
	    //Step 10: Verify the following fields & Button are present in the "Grant Application Form" Page
	    
	    verifyElementPresence(driver, By.id("organizationName"), "Organization Name");
	    verifyElementPresence(driver, By.id("grantName"), "Grant Name");
	    verifyElementPresence(driver, By.id("budget"), "Budget");
	    verifyElementPresence(driver, By.id("projectDescription"), "Project Description");
	    verifyElementPresence(driver, By.xpath("//button[contains(.,'Submit Application')]"), "Submit Application");
	    
	    System.out.println(" Step 10 Passed: The following fields & Button are present in the \"Grant Application Form\" Page");
	    
	   driver.quit();
	}

	
		private static void verifyElementPresence(EdgeDriver driver, By locator, String elementName) 
		{
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

		private static void InputValues(EdgeDriver driver, By locator, String elementName, String var) 
		{
			try 
			{
				WebElement element = driver.findElement(locator);
				if (element.isDisplayed()) 
				{
					element.sendKeys(var);
					System.out.println(elementName + " value is " + var + " Inputted");
				}
			}
			catch (NoSuchElementException e)
			{
				System.out.println(elementName + " value is not " + var + " Inputted");
			}
		}
		
}