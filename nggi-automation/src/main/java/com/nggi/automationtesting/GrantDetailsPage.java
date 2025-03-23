package com.nggi.automationtesting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents the Grants Details page automation.
 * @author John K
 */

public class GrantDetailsPage {
	/**
     * This method initializes the Edge driver, navigates to NGGI Application, and Validates the Grants Details Page Functionality.
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
	    
	    WebElement GrantsDetail_Button = driver.findElement(By.linkText("Grants"));
	    boolean isGrantsDetail_Button = GrantsDetail_Button.isDisplayed();
	    
	    if (isGrantsDetail_Button == true) {
	    	GrantsDetail_Button.click();
		    System.out.println("Grants Detail Button is displayed & It Is Clicked");
	    } else {
	    	System.out.println("Grants Detail Button is Not displayed & It Is Not Clicked");
	    }
	    
	    WebElement GrantsDetail_Teto = driver.findElement(By.xpath("//h2[contains(.,'Teto Grant')]"));
	    boolean isGrantsDetail_Teto = GrantsDetail_Teto.isDisplayed();
	   
	    if (isGrantsDetail_Teto == true) {
	    	String GrantsDetail_Teto_Value = GrantsDetail_Teto.getText();
	    	
		    System.out.println("Grant " + GrantsDetail_Teto_Value + " Is Displayed");
	    } else {
	    	System.out.println("Grant Teto Is Not Displayed");
	    }
	    
	    WebElement GrantsDetail_Pejite = driver.findElement(By.xpath("//h2[contains(.,'Pejite Innovation Grant')]"));
	    boolean isGrantsDetail_Pejite = GrantsDetail_Pejite.isDisplayed();
	   
	    if (isGrantsDetail_Pejite == true) {
	    	String GrantsDetail_Pejite_Value = GrantsDetail_Pejite.getText();
	    	
		    System.out.println("Grant " + GrantsDetail_Pejite_Value + " Is Displayed");
	    } else {
	    	System.out.println("Grant Pejite Is Not Displayed");
	    }
	    
	    WebElement GrantsDetail_Ohmu = driver.findElement(By.xpath("//h2[contains(.,'Ohmu Biodiversity Grant')]"));
	    boolean isGrantsDetail_Ohmu = GrantsDetail_Ohmu.isDisplayed();
	   
	    if (isGrantsDetail_Ohmu == true) {
	    	String GrantsDetail_Ohmu_Value = GrantsDetail_Ohmu.getText();
	    	
		    System.out.println("Grant " + GrantsDetail_Ohmu_Value + " Is Displayed");
	    } else {
	    	System.out.println("Grant Teto Not Is Displayed");
	    }
	    
	    driver.quit();
	}

}
