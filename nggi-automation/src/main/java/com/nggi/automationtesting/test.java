package com.nggi.automationtesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "./Drivers/edgedriver.exe"); //this line refers to the driver in the Driver Folder under this project
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    
	    // Step 1: Verify redirection to NGGI Application Page
	    driver.get("http://localhost:4200");
	    
	    
	    
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
	    	
	    }
	    
	    
	    
	    String pageTitle = driver.getTitle();
	    System.out.println("Page Title: " + pageTitle); // To get Page Title

	}

}
