/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/19/2015
 * @Comments This automation class will serve the Admin Login and Delete Future Change Request.
 */
package com.fisoc.admin.helpers;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class DeleteFutureChangeRequestTestHelper 
{
	public static String adminLoginEmail;
	public static String adminLoginPassword;
	public static String merchantTitle;
	public static String multiplier;
	public static String programTier;
	public static String merchantStatus;
	public static String merchantType;
	public static String directoryFeeSchedule;
	public static String documentDate;
	public static String effectiveDate;
	public static String merchantNotes;
	public static Boolean adminPageLoginStatus;
	public static Boolean isRemoveChangeRequestPresent;
	public static String changeRequestSuccess;
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(DeleteFutureChangeRequestTestHelper.class);
	AdminLoginPageTestHelper adminLoginLogout = new AdminLoginPageTestHelper();
	
	/**
	 * Test Case for Reading the excel data and login into the admin portal
	 * Input: WebDriver
	 * Output: Void
	 */
	public void deleteFutureChangeRequestInitialPage(WebDriver driver)
	{
		try
		{
			 rowCount= xllib.getRowCount("AdminLogin");
			 for (int i = 1; i <= rowCount; i++) 
			 {
				adminLoginEmail = xllib.getExcelData("AdminLogin", i, 0);
				adminLoginPassword = xllib.getExcelData("AdminLogin", i, 1);
				adminPageLoginStatus = adminLoginLogout.adminPageLoginActions(driver,adminLoginEmail,adminLoginPassword);
				//Check admin login status
				if(adminPageLoginStatus == true)
				{
					deleteFutureChangeRequestActions(driver);
					adminLoginLogout.adminPageLogoutActions(driver);
				}
			 }
		}
		catch(Exception e)
		{
			log.info("Test case failed to execute");
		}
	}
	
	/**
	 * Test Case for Deleting Future Change Request.
	 * on correct data we can successfully Delete Future Change Request.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void deleteFutureChangeRequestActions(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			int rowCount =xllib.getRowCount("ChangeFutureRequest");
			for (int i=1;i<=rowCount;i++)
			{
				merchantTitle=xllib.getExcelData("ChangeFutureRequest", i,0);
				
				log.info("Browsing all Merchants");	
				driver.findElement(By.id("module_btn_browse_all_merchants")).click();
				Thread.sleep(5000);
				driver.findElement(By.name("namefilter")).clear();
				driver.findElement(By.name("namefilter")).sendKeys(merchantTitle);
				driver.findElement(By.xpath("//a[contains(text(),'Filter')]")).click();			
				driver.findElement(By.name("statusfilter")).click();
				driver.findElement(By.id("btnSearch")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[contains(text(),'"+merchantTitle+"')]")).click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//a[@href='change-requests']")).click();
				Thread.sleep(5000);
				log.info("Clicking on Merchant Change Request");
				
				isRemoveChangeRequestPresent = driver.findElement(By.xpath("//button[contains(text(),'Remove Change Request')]")).isDisplayed();
				//Check Remove Change Request is displayed or not. 
				if(isRemoveChangeRequestPresent){
					driver.findElement(By.xpath("//button[contains(text(),'Remove Change Request')]")).click();
					log.info("Checking Merchant Change Request is available to remove.");
					driver.findElement(By.id("edit_merchant_save")).click();
					Thread.sleep(5000);
					changeRequestSavedAlertMessage(driver);
				}else{
					log.info("Merchant Change Request is unavailable to remove.");
				}
			}
		}
		catch(Exception e)
		{
			log.info("Merchant Change Request is not removed.");
		}
	}
	
	/**
	 * Method for handling Change Request Alert Message.
	 * on click alert Ok we can successfully Delete Future Change Request.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeRequestSavedAlertMessage(WebDriver driver) throws InterruptedException {
		
		Alert alert = driver.switchTo().alert();
		changeRequestSuccess = alert.getText();
		alert.accept(); 
		if(changeRequestSuccess.equalsIgnoreCase(TestConstants.CHANGES_SAVED)){
			log.info("Merchant Change Request Successfully Removed");
		}else{
			log.info("Merchant Change Request is not removed.");
		}
	}
}