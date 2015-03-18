/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/16/2015
 * @Comments:This automation class will login into User Portal and visit Activity Page. 
**/
package com.fisoc.user.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class UserActivityPageTestHelper 
{
	 public static String financialInstitutionLoginEmail;
	 public static String financialInstitutionLoginPassword;
	 public static String financialInstitutionName;
	 public static String financialInstitutionUrl;
	 public static String merchantTitle;
	 public static String location;
	 public static int tommorrowDate;
	 public static String fromDate;
	 public static String toDate;
	 public static String searchActivity;
	 
	 boolean present;
	 ExcelLib xllib = new ExcelLib();
	 TestConstants constants = new TestConstants();
	 UserLoginPageTestHelper userLogin = new UserLoginPageTestHelper();
	 private static Logger log = Logger.getLogger(UserLoginPageTestHelper.class);
	 
	 /**
	 * Test Case for Reading the excel data and login into the user
	 * portal on successful login,visit the Activity Page.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void userActivityInitialPage(WebDriver driver)
	 {
		try
		{
			int rowCount = xllib.getRowCount("UserLogin");
			for(int i=1;i<= rowCount;i++)
			{
				financialInstitutionLoginEmail = xllib.getExcelData("UserLogin", i, 0);
				financialInstitutionLoginPassword = xllib.getExcelData("UserLogin", i, 1);
				financialInstitutionName = xllib.getExcelData("UserLogin",i,2); 
				financialInstitutionUrl = constants.financialInstitutionUrlConvertActions(financialInstitutionName);
    			Boolean userloggedInStatus = userLogin.userLoginPageActions(driver, financialInstitutionLoginEmail, financialInstitutionLoginPassword,financialInstitutionName,financialInstitutionUrl);
				//Check user logged in status
				if(userloggedInStatus == true)
				{
					userActivityView(driver);
					userLogin.userLogoutPageActions(driver);
				}
			}
		}
		catch(Exception e)
		{
			
		}
	 }
	
	/**
	 * Test Case for login into user portal
	 * Upon correct login data we can successfully login and visit User Activity Page. 
	 * Input: WebDriver
	 * Output: Boolean
	 * @throws InvalidFormatException
	 */	 
	public boolean userActivityView(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			int rowCount = xllib.getRowCount("UserActivity");
			for(int i=1;i<= rowCount;i++)
			{
				location = xllib.getExcelData("UserActivity", i,0);
				searchActivity = xllib.getExcelData("UserActivity", i,1);
				fromDate = xllib.getExcelData("UserActivity", i,2);
				toDate = xllib.getExcelData("UserActivity", i,3);
				
				DateFormat dateformat = new SimpleDateFormat("dd");
	   			Date date = new Date();
	   			String dates = dateformat.format(date);
	   			tommorrowDate= Integer.parseInt(dates)+1;
	   			
	   			/*driver.findElement(By.id("location")).clear();
				driver.findElement(By.id("location")).sendKeys(location);
				driver.findElement(By.id("locationGo")).click();*/
				log.info("Viewing User Recent Activities");
				driver.findElement(By.xpath("//a[@href='#/activity']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(5000);
				/*driver.findElement(By.id("activity_searchTerms")).clear();
				driver.findElement(By.id("activity_searchTerms")).sendKeys(searchActivity);
				driver.findElement(By.id("activity_search")).click();
				driver.findElement(By.id("from_date")).clear();
				driver.findElement(By.id("from_date")).sendKeys(fromDate);*/
				driver.findElement(By.id("to_date")).clear();
				driver.findElement(By.id("to_date")).sendKeys(toDate);
				driver.findElement(By.xpath("//a[@href='#"+tommorrowDate+"']")).click();
	   		}
		}
		catch(Exception e)
		{
			log.info("Error in User Activity Page");
		}
		return present;		
	}
}
