/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/20/2015
 * @Comments:This automation class will login into User Portal and View My Purchases.
**/
package com.fisoc.user.helpers;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class ViewMyPurchasesTestHelper 
{
	 public static String financialInstitutionLoginEmail;
	 public static String financialInstitutionLoginPassword;
	 public static String financialInstitutionName;
	 public static String financialInstitutionUrl;
	 public static String merchantTitle;
	 public static String location;
	 boolean present;
	 ExcelLib xllib = new ExcelLib();
	 TestConstants constants = new TestConstants();
	 UserLoginPageTestHelper userLoginLogout = new UserLoginPageTestHelper();
	 private static Logger log = Logger.getLogger(UserLoginPageTestHelper.class);
	 
	 /**
	 * Test Case for Reading the excel data and login into the user
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void viewMyPurchasesInitialPage(WebDriver driver)
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
    			Boolean userloggedInStatus = userLoginLogout.userLoginPageActions(driver, financialInstitutionLoginEmail, financialInstitutionLoginPassword,financialInstitutionName,financialInstitutionUrl);
				//Check user logged in status
				if(userloggedInStatus == true)
				{
					viewMyPurchasesActions(driver);
					//userLoginLogout.userLogoutPageActions(driver);
				}
			}
		}
		catch(Exception e)
		{
			
		}
	 }
	
	 /**
	 * Test Case for login into user portal
	 * Upon correct login data we can successfully login and View My Purchases.
	 * Input: WebDriver
	 * Output: Boolean
	 * @throws InvalidFormatException
	 */	 
	public boolean viewMyPurchasesActions(WebDriver driver) 
	{
		try
		{
			int rowCount = xllib.getRowCount("UserLogin");
			for(int i=1;i<= rowCount;i++)
			{
				merchantTitle = xllib.getExcelData("SetUpMerchanteReward", i,0);
				location = xllib.getExcelData("SetUpMerchanteReward", i,8);
				userLoginLogout.setLocationAction(driver, location);				
				Thread.sleep(5000);
				driver.findElement(By.id("fbphoto")).click();
				Thread.sleep(5000);
				log.info("View Rewards in My Purchases");
				driver.findElement(By.id("show_myrewards_action_elem")).click();
				Thread.sleep(5000);
				log.info("View and print Rewards");
				driver.findElement(By.xpath("//a[text()='(View & Print)']")).click();
			}
		}
		catch(Exception e)
		{
			
		}
		return present;
	}
}
