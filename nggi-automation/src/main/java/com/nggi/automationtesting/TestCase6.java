package com.nggi.automationtesting;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents Test Case 6 in Quality Plus in JIRA, using TestNG framework.
 * It Verifies that the Admin users can access the NGGI Application & Navigates to the Admin Dashboard Page. Test Case Execution
 * @author John K
 */

public class TestCase6 {

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
	    	InputValues(driver, By.id("email"), "Email", "Sherineanbaiyan18@gmail.com");
	        System.out.println(" Step 4 Passed: Email Id has been Inputted");
	    }
  	    else
  	    {
  	    	System.out.println(" Step 4 Failed: Email Id has Not been Inputted");
  	    }
	    
	    if (isSignIn_PasswordButton == true)
  	    {
	        InputValues(driver, By.id("password"), "Password", "Password@1234"); 
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
	    
	    // Step 6: Verify user is navigated to the Admin page upon successful user login.
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
	            System.out.println(" Step 6 Passed: The user is navigated to the Admin page upon successful user login.");
	            SignIn_Message.click();
	        } 
	        else 
	        {
	            System.out.println("Login Sucessfull Message Is Not Displayed");
	            System.out.println(" Step 6 Failed: The user is Not navigated back to the Admin page upon successful user login.");
	        }
	    } 
	    else 
	    {
	    	System.out.println("Message is Not Displayed");
	    }
	    
	    
	    // Step 7: Verify the "Admin Dashboard" Page is Displayed post click on the "Sign-In" Button in the Login Page.
	    WebElement ADminDashboard_Element = driver.findElement(By.xpath("//h1[contains(.,'Application Overview')]"));
	    boolean isADminDashboard_ElementDisplayed = ADminDashboard_Element.isDisplayed();
	  
	    
	    if (isADminDashboard_ElementDisplayed == true) 
	    {
	    	System.out.println(" Step 7: Admin Dashboard is Displayed"); 
	    } 
	    else 
	    {
	    	System.out.println(" Step 7: Admin Dashboard is Not Displayed");
	    }
	    
	    	    
	   driver.quit();
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