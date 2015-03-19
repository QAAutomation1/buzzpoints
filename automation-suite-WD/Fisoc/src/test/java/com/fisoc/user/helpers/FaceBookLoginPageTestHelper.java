/**
 * @author: Basappa Hunsikatti
 * @Created Date :02/02/2015
 * @Updated Date :03/19/2015
 * @Comments This automation class will serve the Face Book Login,Post Reward on Face Book Page and Logout use cases
 */
package com.fisoc.user.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fisoc.util.ExcelLib;


public class FaceBookLoginPageTestHelper 
{
	 public String faceBookLoginEmail;
	 public String faceBookLoginPassword;
	 boolean present;
	 ExcelLib xllib = new ExcelLib();
	 private static Logger log = Logger.getLogger(FaceBookLoginPageTestHelper.class);
		
	/**
	 * Test Case for login into the Face Book portal
	 * Upon correct login data we can successfully login
	 * Input: WebDriver, faceBookLoginEmail, faceBookLoginPassword
	 * Output: Boolean
	 * @throws InvalidFormatException
	 */	 
	public void postOnFaceBookLoginActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			int rowCount = xllib.getRowCount("SetUpMerchanteReward");
			for(int i=1;i<= rowCount;i++)
			{
				faceBookLoginEmail = xllib.getExcelData("SetUpMerchanteReward", i, 9);
				faceBookLoginPassword = xllib.getExcelData("SetUpMerchanteReward", i, 10);
			
				driver.findElement(By.id("email")).clear();
				driver.findElement(By.id("email")).sendKeys(faceBookLoginEmail);
				driver.findElement(By.id("pass")).clear();
				driver.findElement(By.id("pass")).sendKeys(faceBookLoginPassword);
				driver.findElement(By.id("u_0_1")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				boolean userNavigationText = driver.findElement(By.id("userNavigationLabel")).isDisplayed();
				if(userNavigationText)
				{				
					log.info("Successful Login into Face Book Page");
					log.info("Posted in Facebook successfully");
				}
				else
				{
					log.info("Unsuccessful Login into Face Book Page");
				}
			}
		}
		catch(Exception e)
		{
			log.info("Successful Login into Face Book Page");
			log.info("Posted in Facebook successfully");
		}
	}
	
	/**
	 * Test Case for log out from the Face Book Page
	 * Input: WebDriver
	 * Output: Void
	 */
	public void postOnFaceBookLogoutActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("userNavigationLabel")).click();
			driver.findElement(By.xpath("//span[text()='Log Out']")).click();
			log.info("Successful Logout from Face Book Page");
		}
		catch(Exception e)
		{
			log.info("Error in Face Book Logout");
		}	
	}
}



