/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/19/2015
 * @Comments This automation class will serve the Admin Login and Change Future Request.
 */
package com.fisoc.admin.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class FutureChangeRequestTestHelper 
{
	public static String adminLoginEmail;
	public static String adminLoginPassword;
	public static String numOfTransactions;
	public static String minTransactionAmount;
	public static String eLoyaltyStartDate;
	public static String eLoyaltyEndDate;
	public static String merchantTitle;
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(FutureChangeRequestTestHelper.class);
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
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					adminLogin.adminPageLogoutActions(driver);					
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
				multiplier = xllib.getExcelData("ChangeFutureRequest", i,0);
				programTier = xllib.getExcelData("ChangeFutureRequest", i,1);
				merchantStatus = xllib.getExcelData("ChangeFutureRequest", i,2);
				merchantType = xllib.getExcelData("ChangeFutureRequest", i,3);
				directoryFeeSchedule = xllib.getExcelData("ChangeFutureRequest", i,4);
				documentDate = xllib.getExcelData("ChangeFutureRequest", i,5);
				effectiveDate = xllib.getExcelData("ChangeFutureRequest", i,6);
				merchantNotes = xllib.getExcelData("ChangeFutureRequest", i,7);
				
				log.info("Browsing all Merchants");	
				driver.findElement(By.id("module_btn_browse_all_merchants")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(By.name("namefilter")).clear();
				driver.findElement(By.name("namefilter")).sendKeys(merchantTitle);
				driver.findElement(By.xpath("//a[contains(text(),'Filter')]")).click();			
				driver.findElement(By.name("statusfilter")).click();
				driver.findElement(By.id("btnSearch")).click();
				driver.findElement(By.xpath("//a[contains(text(),'"+merchantTitle+"')]")).click();
				
				driver.findElement(By.xpath("//a[@href='change-requests']")).click();
				log.info("Clicking on Merchant Change Request");
				driver.findElement(By.xpath("//a[@id='merchantChangeRequests_showAddChangeRequest']")).click();
				driver.findElement(By.xpath("//div[label[@for='multiplier']]/div/div/input")).sendKeys(multiplier);
				driver.findElement(By.id("tier")).sendKeys(programTier);
				driver.findElement(By.id("status")).sendKeys(merchantStatus);
				driver.findElement(By.id("type")).sendKeys(merchantType);
				driver.findElement(By.xpath("//*[@id='edit_merchant_change_request_detail']/div[2]/div/div[2]/div[1]/div/form/div[5]/div/div/input")).sendKeys(directoryFeeschedule);
				driver.findElement(By.xpath("//input[@name='documentDate']")).sendKeys(documentDate);
				driver.findElement(By.xpath("//input[@name='effectiveDate']")).sendKeys(effectiveDate);
				driver.findElement(By.xpath("//textarea[@name='notes']")).sendKeys(merchantNotes);
							
				driver.findElement(By.id("edit_merchant_savechange_request")).click();
				log.info("Set Merchant Change Request");
				driver.findElement(By.id("edit_merchant_save")).click();
								
				Alert alert = driver.switchTo().alert();
				alert.accept(); 
				Thread.sleep(5000);
				alert = driver.switchTo().alert();
				String rewardSuccess = alert.getText();
				alert.accept(); 
				if(rewardSuccess.equalsIgnoreCase(TestConstants.CHANGE_REQUESTS_SAVED)){
					log.info("Merchant Change requests saved");
				}else{
					log.info("Merchant Change requests not saved");
				}
			}
		}
		catch(Exception e)
		{
			log.info("Loyalty Card not saved");
		}
	}

	public void loyaltyCardAlertMessage(WebDriver driver) {
		
		Alert alert = driver.switchTo().alert();
		String loyaltyCardSuccess = alert.getText();
		alert.accept(); 
		//Check whether Loyalty Card is saved or not
		if(loyaltyCardSuccess.equalsIgnoreCase(TestConstants.CHANGES_SAVED))
		{
			log.info("Loyalty Card successfully saved");
		}
		else
		{
			log.info("Loyalty Card not saved");
		}
	}
}