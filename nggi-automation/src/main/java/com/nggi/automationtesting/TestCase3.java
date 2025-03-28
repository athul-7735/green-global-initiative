package com.nggi.automationtesting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents the Test Case 3 in Quality Plus in JIRA.
 * @author John K
 */

public class TestCase3 {
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

		    if (driver.getTitle().contains("GreenGlobalInitiative")) 
		    {
	            System.out.println(" Step 1 Passed: Redirected to NGGI Application Page.");
	        } else {
	            System.out.println(" Step 1 Failed: Redirection to NGGI Application Page failed.");
	        }
		 
		    
	        // Prerequisite Step 1: Verify SignIn/SignUp button is present
		    WebElement SignInButton = driver.findElement(By.linkText("SignIn/SignUp"));
		    boolean isSignInButtonDisplayed = SignInButton.isDisplayed();
		    
		    if (isSignInButtonDisplayed == true)
		    {	    
			    System.out.println(" Prerequisite Step 1 Passed: SignIn/SignUp button is present.");
		    }
		    else
		    {
		    	System.out.println(" Prerequisite Step 1 Failed: SignIn/SignUp button not found");
		    }
		    
		    // Prerequisite Step 2: Verify navigation to Login Page on clicking SignIn/SignUp button
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
		  	    	System.out.println(" Prerequisite Step 2 Passed: The user is able to navigate to the Login Page on clicking Sign-In/Sign-Up Button in the NGGI Application Page.");
		  	    }
		  	    else
		  	    {
		  	    	System.out.println(" Prerequisite Step 2 Failed: The user is Not Able to navigate to the Login Page on clicking Sign-In/Sign-Up Button in the NGGI Application Page.");
		  	    }
		    } 
		    else
		  	{
		    	System.out.println(" Prerequisite Step 2 Failed: SignIn/SignUp Button Not Found & Not Clicked");
		  	}

	        // Prerequisite Step 3: Verify user is able to logging to NGGI Application 
		    WebElement SignIn_SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        InputValues(driver, By.id("email"), "Email", "JamesBond@gmail.com");
	        InputValues(driver, By.id("password"), "Password", "JamesBond@12345"); 
	        SignIn_SubmitButton.click();
	
		    WebElement SignIn_Message = driver.findElement(By.id("toast-container"));
		    boolean isSignIn_Message = SignIn_Message.isDisplayed();
		   
		    String SignIn_alertText = SignIn_Message.getText();
		    System.out.println("Alert message: " + SignIn_alertText);
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		    if (isSignIn_Message == true) 
		    {
		    	System.out.println("Message is Displayed");
		    	// Check if the alert message contains "Login successful"
		        if (SignIn_alertText.contains("Login Successfull")) {
		            System.out.println("Login Sucessfull Message Is Displayed");
		            System.out.println(" Prerequisite Step 3 Passed: Redirected to NGGI Application Home Page.");
		            SignIn_Message.click();
		        } 
		        else {
		            System.out.println("Login Sucessfull Message Is Not Displayed");
		            System.out.println(" Prerequisite Step 3 Failed: 2. Redirection to NGGI Application Home Page failed.");
		          
		        }
		    } 
		    else 
		    {
		    	System.out.println("Message is Not Displayed");
		    } 
		    
		    // Step 2: Verify the header section of the NGGI Application Page
		     verifyElementPresence(driver, By.linkText("Home"), "Home");
		     verifyElementPresence(driver, By.linkText("About"), "About");
		     verifyElementPresence(driver, By.linkText("Grants"), "Grants");
		     //Need to add 3 button here for step2 
		     
		     
			// Step 3: Verify that the home page of the application contains "View All Grants" Button.
		     verifyElementPresence(driver, By.xpath("//button[contains(.,'View All Grants')]"), "View All Grants");
		     
		    // Step 4: Verify On Clicking the "View All Grants" Button in the Home Page or on Clicking the "Grants" Button from the Header section, it navigates to the "All Grants Details" Page.
		     WebElement Home_ViewAllGrantsButon = driver.findElement(By.xpath("//button[contains(.,'View All Grants')]"));
		     Home_ViewAllGrantsButon.click();
		     
		     WebElement ViewAllGrant_Title = driver.findElement(By.xpath("//h3[contains(.,'Our Grants')]"));
			 boolean isViewAllGrant_TitleDisplayed = ViewAllGrant_Title.isDisplayed();
			    
			    if (isViewAllGrant_TitleDisplayed == true)
			    {	    
				    System.out.println(" Step 4 Passed: On clicking the \"View All Grants\" Button in the Home Page or on clicking the \"Grants\" Button from the Header section, user is able to navigate to the \"All Grants Details\" Page.");
			    }
			    else
			    {
			    	System.out.println(" Step 4 Failed: On clicking the \"View All Grants\" Button in the Home Page or on clicking the \"Grants\" Button from the Header section, user is Not able to navigate to the \"All Grants Details\" Page.");
			    }
		     
			 // Step 5: Verify the "All Grants Details" page contains 3 types of grants. 
		     verifyElementPresence(driver, By.xpath("//h2[contains(.,'Teto Grant')]"), "Teto Grant");
		     verifyElementPresence(driver, By.xpath("//h2[contains(.,'Pejite Innovation Grant')]"), "Pejite Innovation Grant");
		     verifyElementPresence(driver, By.xpath("//h2[contains(.,'Ohmu Biodiversity Grant')]"), "Ohmu Biodiversity Grant");
		    
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



