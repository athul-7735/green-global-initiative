package com.nggi.automationtesting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents the Grants Application Form Page automation.
 * @author John K
 */
public class GrantFormPage {
	/**
     * This method initializes the Edge driver, navigates to NGGI Application, and Validates the Grants Application Form Page Functionality.
     * @param args Command line arguments (not used in this method).
     */
	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe"); //this line refers to the driver in the Driver Folder under this project
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.get("http://localhost:4200/");
	    WebElement homeButton = driver.findElement(By.linkText("Home"));
	    boolean isHomeButtonDisplayed = homeButton.isDisplayed();
	    System.out.println("Is the Home button displayed? " + isHomeButtonDisplayed);
	    try {
	        // Locate the SignIn/SignUp button
	        WebElement signInButton = driver.findElement(By.linkText("SignIn/SignUp"));
	        
	        // Click on the button if it exists
	        signInButton.click();
	        System.out.println("SignIn/SignUp Button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        // Handle the case where the button is not found
	        System.out.println("SignIn/SignUp button not available.");
	        // Stop execution by throwing an exception
	        throw new RuntimeException("Execution stopped as button was not found.");
	    }
	    WebElement SignIn_EmailButton = driver.findElement(By.id("email"));
	    WebElement SignIn_PasswordButton = driver.findElement(By.id("password"));
	    WebElement SignIn_SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	    
	    
	    boolean isSignIn_EmailButton = SignIn_EmailButton.isDisplayed();
	    boolean isSignIn_PasswordButton = SignIn_PasswordButton.isDisplayed();
	    boolean isSignIn_SubmitButton = SignIn_SubmitButton.isDisplayed();
	    
	    
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

	}

}
