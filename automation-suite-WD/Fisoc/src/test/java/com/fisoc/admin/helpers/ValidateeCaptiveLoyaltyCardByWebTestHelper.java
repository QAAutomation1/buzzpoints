/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/20/2015
 * @Comments This automation class will serve the Admin Login and Validate eCaptive Loyalty Card By Web.
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

public class ValidateeCaptiveLoyaltyCardByWebTestHelper 
{
	public static String adminLoginEmail;
	public static String adminLoginPassword;
	public static String financialInstitution;
	public static String financialInstitutionEmailId;
	public static String redemptionDate;
	public static String merchantTitle;
	public static String redemptionValidateByWebText;
	public Boolean adminPageLoginStatus;
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(ValidateeCaptiveLoyaltyCardByWebTestHelper.class);
	AdminLoginPageTestHelper adminLoginLogout = new AdminLoginPageTestHelper();
	TestConstants constants = new TestConstants();
	
	/**
	 * Test Case for Reading the excel data and login into the admin portal
	 * Input: WebDriver
	 * Output: Void
	 */
	public void validateLoyaltyCardByWebInitialPage(WebDriver driver)
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
					validateLoyaltyCardByWebActions(driver);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	 * Test Case for Validating eCaptive Loyalty Card By Web.
	 * on correct data we can successfully validate eCaptive Loyalty Card By Web.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void validateLoyaltyCardByWebActions(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			int rowCount =xllib.getRowCount("SetUpMerchantLoyaltyCard");
			for (int i=1;i<=rowCount;i++)
			{
				merchantTitle=xllib.getExcelData("SetUpMerchantLoyaltyCard", i,0);
				financialInstitution = xllib.getExcelData("SetUpMerchantLoyaltyCard", i,5);
				financialInstitutionEmailId = xllib.getExcelData("SetUpMerchantLoyaltyCard", i,6);
				redemptionDate = xllib.getExcelData("SetUpMerchantLoyaltyCard", i,7);
								
				driver.findElement(By.id("module_btn_manage_redemptions")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("Clicking on Manage Redemptions");
				
				WebElement financialInstitutionValue =driver.findElement(By.name("fiidfilter"));
				Select st= new Select(financialInstitutionValue);
				st.selectByVisibleText(financialInstitution);
				
				WebElement merchantRedemptionStatus=driver.findElement(By.name("statusfilter"));
				Select st1= new Select(merchantRedemptionStatus);
				st1.selectByIndex(5);
				
				/*WebElement resultsPerPage=driver.findElement(By.name("pagelength"));
				Select st2 = new Select(resultsPerPage);
				st2.selectByIndex(0);*/
				
				driver.findElement(By.name("useremailfilter")).clear();
				driver.findElement(By.name("useremailfilter")).sendKeys(financialInstitutionEmailId);
				driver.findElement(By.name("redemptiondatefilter")).clear();
				driver.findElement(By.name("redemptiondatefilter")).sendKeys(redemptionDate);
				driver.findElement(By.name("merchanttitlefilter")).clear();
				driver.findElement(By.name("merchanttitlefilter")).sendKeys(merchantTitle);
				driver.findElement(By.id("btnSearch")).click();
				
				//Boolean isMerchantTitlePresent = driver.findElement(By.xpath("//div[contains(text(),'"+merchantTitle+"')]")).isDisplayed();
				String merchantTitleValue = driver.findElement(By.xpath("//div[contains(text(),'"+merchantTitle+"')]")).getText();
				String points = driver.findElement(By.xpath("//tr[@module='manage_redemptions/edit_redemption']/td[5]")).getText();
				
				System.out.println(merchantTitleValue);
				System.out.println(points);
				
				int pointsValue = constants.convertingStringToInteger(driver,points);
				
				System.out.println(pointsValue); 
				
				if(pointsValue > 0){
					
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[contains(text(),'"+merchantTitle+"')]")).click();
					driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
					driver.findElement(By.xpath("//a[@bindaction='buzzapp.module.manage_redemptions.edit_redemption.validatebyweb']")).click();
					validateLoyaltyCardByWebAlertMessage(driver);
				}
			}
		}
		catch(Exception e)
		{
			log.info("Redemption is not validated.");
		}
	}
	
	/**
	 * Method for  Validating eCaptive Loyalty Card By Web Alert Message.
	 * on click alert Ok we can successfully  Validating eCaptive Loyalty Card
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void validateLoyaltyCardByWebAlertMessage(WebDriver driver) {
		
		Alert alert = driver.switchTo().alert();
		String redemptionValidateByWebText = alert.getText();
		alert.accept(); 
		if(redemptionValidateByWebText.equalsIgnoreCase(TestConstants.MERCHANT_REDEMPTION_VALIDATION_BY_WEB))
		{
			log.info("Redemption is successfully validated.");
		}else
		{
			log.info("Redemption is not validated.");
		}
	}
}