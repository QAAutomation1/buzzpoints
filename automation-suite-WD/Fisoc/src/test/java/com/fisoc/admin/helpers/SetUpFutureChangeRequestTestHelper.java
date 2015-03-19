/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/19/2015
 * @Comments This automation class will serve the Admin Login and Set up Future Change Request.
 */
package com.fisoc.admin.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class SetUpFutureChangeRequestTestHelper 
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
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(SetUpFutureChangeRequestTestHelper.class);
	AdminLoginPageTestHelper adminLogin = new AdminLoginPageTestHelper();
	
	/**
	 * Test Case for Reading the excel data and login into the admin portal
	 * Input: WebDriver
	 * Output: Void
	 */
	public void futureChangeRequestInitialPage(WebDriver driver)
	{
		try
		{
			 rowCount= xllib.getRowCount("AdminLogin");
			 for (int i = 1; i <= rowCount; i++) 
			 {
				adminLoginEmail = xllib.getExcelData("AdminLogin", i, 0);
				adminLoginPassword = xllib.getExcelData("AdminLogin", i, 1);
						
				Boolean adminPageLoginStatus = adminLogin.adminPageLoginActions(driver,adminLoginEmail,adminLoginPassword);
				//Check admin login status
				if(adminPageLoginStatus == true)
				{
					futureChangeRequestActions(driver);
					//adminLogin.adminPageLogoutActions(driver);					
				}
			 }
		}
		catch(Exception e)
		{
			log.info("Test case failed to execute");
		}
	}
	
	/**
	 * Test Case for Changing Future Request
	 * on correct data we can successfully Change Future Request
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void futureChangeRequestActions(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			int rowCount =xllib.getRowCount("ChangeFutureRequest");
			for (int i=1;i<=rowCount;i++)
			{
				merchantTitle=xllib.getExcelData("ChangeFutureRequest", i,0);
				multiplier = xllib.getExcelData("ChangeFutureRequest", i,1);
				programTier = xllib.getExcelData("ChangeFutureRequest", i,2);
				merchantStatus = xllib.getExcelData("ChangeFutureRequest", i,3);
				merchantType = xllib.getExcelData("ChangeFutureRequest", i,4);
				directoryFeeSchedule = xllib.getExcelData("ChangeFutureRequest", i,5);
				documentDate = xllib.getExcelData("ChangeFutureRequest", i,6);
				effectiveDate = xllib.getExcelData("ChangeFutureRequest", i,7);
				merchantNotes = xllib.getExcelData("ChangeFutureRequest", i,8);
				
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
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("merchantChangeRequests_showAddChangeRequest")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[label[@for='multiplier']]/div/div/input")).clear();
				driver.findElement(By.xpath("//div[label[@for='multiplier']]/div/div/input")).sendKeys(multiplier);
				driver.findElement(By.id("tier")).clear();
				driver.findElement(By.id("tier")).sendKeys(programTier);
				driver.findElement(By.id("status")).clear();
				driver.findElement(By.id("status")).sendKeys(merchantStatus);
				driver.findElement(By.id("type")).clear();
				driver.findElement(By.id("type")).sendKeys(merchantType);
				driver.findElement(By.xpath("//*[@id='edit_merchant_change_request_detail']/div[2]/div/div[2]/div[1]/div/form/div[5]/div/div/input")).clear();
				driver.findElement(By.xpath("//*[@id='edit_merchant_change_request_detail']/div[2]/div/div[2]/div[1]/div/form/div[5]/div/div/input")).sendKeys(directoryFeeSchedule);
				driver.findElement(By.name("documentDate")).clear();
				driver.findElement(By.name("documentDate")).sendKeys(documentDate);
				driver.findElement(By.name("effectiveDate")).clear();
				driver.findElement(By.name("effectiveDate")).sendKeys(effectiveDate);
				driver.findElement(By.name("notes")).clear();
				driver.findElement(By.name("notes")).sendKeys(merchantNotes);
				log.info("Setting up a Merchant Change Request");
				driver.findElement(By.id("edit_merchant_savechange_request")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("edit_merchant_save")).click();
				Thread.sleep(5000);
				changeRequestSavedAlertMessage(driver);
				Thread.sleep(5000);
			}
		}
		catch(Exception e)
		{
			log.info("Merchant Change Request not saved");
		}
	}
	
	/**
	 * Method for handling Change Request Alert Message.
	 * on click alert Ok we can successfully Set Up Future Change Request.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeRequestSavedAlertMessage(WebDriver driver) throws InterruptedException {
		
		Alert alert = driver.switchTo().alert();
		String changeRequestSuccess = alert.getText();
		alert.accept(); 
		if(changeRequestSuccess.equalsIgnoreCase(TestConstants.CHANGES_SAVED)){
			log.info("Merchant Change Request Successfully Saved");
		}else{
			log.info("Merchant Change Request Not Saved");
		}
	}
}