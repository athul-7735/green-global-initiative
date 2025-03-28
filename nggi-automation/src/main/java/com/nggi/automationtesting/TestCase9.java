package com.nggi.automationtesting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class represents the Test Case 9 in Quality Plus in JIRA.
 * @author John K
 */

public class TestCase9 {
		/**
	     * This method initializes the Edge driver, navigates to NGGI Application, Navigates to the "About us" Page in the NGGI Application.
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
		    
		    // Step 2: Verify that the user can click on "About Us" Button from the header section of the NGGI Application Page.
		    WebElement AboutButton = driver.findElement(By.linkText("About"));
		    boolean isAboutButtonDisplayed = AboutButton.isDisplayed();
		    
		    if (isAboutButtonDisplayed == true)
		    {	    
			    System.out.println(" Step 2 Passed: \"About Us\" button is present.");
			    AboutButton.click();
			    System.out.println(" Step 2 Passed: The user is able to click on \"About Us\" Button from the header section of the NGGI Application Page..");
		    }
		    else
		    {
		    	System.out.println(" Step 2 Failed: \"About Us\" button not found");
		    	System.out.println(" Step 2 Failed: The user is Not able to click on \"About Us\" Button from the header section of the NGGI Application Page..");
		    }
		    
		    // Step 3: Verify that the user is navigated to the "About Us" Page post click on the "About Us" Button & 
		    WebElement AboutUs_Text = driver.findElement(By.xpath("//app-aboutus/div/h1"));
		    boolean isAboutUs_TextDisplayed = AboutUs_Text.isDisplayed();
		    
		    if (isAboutUs_TextDisplayed == true)
		    {
		    	System.out.println(" Step 3 Passed: The user is Navigated to the \"About Us\" Page post click on the \"About Us\" Button.");
		    }
		    else
		    {
		    	System.out.println(" Step 3 Failed: The user is Not Navigated to the \"About Us\" Page post click on the \"About Us\" Button");
		    }
		    
		    // Step 3: Validate the "About Us" Page displays "Mission", "Vision", "Core Values", Achievement" Section.
	        verifyElementPresence(driver, By.xpath("//h5[contains(.,'MISSION ON')]"), "MISSION");
	        verifyElementPresence(driver, By.xpath("//h5[contains(.,'VISION ON')]"), "VISSION");
	        verifyElementPresence(driver, By.xpath("//h5[contains(.,'ACHIEVEMENTS')]"), "ACHIEVEMENTS");
	        verifyElementPresence(driver, By.xpath("//h5[contains(.,'CORE VALUES')]"), "CORE VALUES");
  
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
	}



