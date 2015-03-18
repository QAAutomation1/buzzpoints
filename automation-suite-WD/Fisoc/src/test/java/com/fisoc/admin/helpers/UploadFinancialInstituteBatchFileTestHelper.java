/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/13/2015
 * @Comments This automation class will serve the Admin Login and browse uploading the Financial Institution Batch File(s) use cases.
 */
package com.fisoc.admin.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fisoc.util.ExcelLib;

public class UploadFinancialInstituteBatchFileTestHelper 
{
	public static String adminLoginEmail;
	public static String adminLoginPassword;
	public static String financialInstitutionSearch;
	public static String financialInstitutionAccountToken;
	public static String transactionDate;
	public static String transactionDescription;
	public static String transactionType;
	public static String transactionStatus;
	public static String transactionBatchId;
	public static String resultsPerPage;
	public String financialInstitutionName;
	public String batchFileDescription;
	public String uploadBatchFileLocation;
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(UploadFinancialInstituteBatchFileTestHelper.class);
	AdminLoginPageTestHelper adminLogin = new AdminLoginPageTestHelper();
	
	
	/**
	 * Test Case for Reading the excel data and login into the admin
	 * portal on successful login we can upload batch file
	 * Input: WebDriver
	 * Output: Void
	 */
	public void uploadBatchFileInitialPage(WebDriver driver)
	{
		try
		{
			 rowCount= xllib.getRowCount("AdminLogin");
			 for (int i = 1; i <= rowCount; i++) 
			 {
				adminLoginEmail = xllib.getExcelData("AdminLogin", i, 0);
				adminLoginPassword = xllib.getExcelData("AdminLogin", i, 1); 
				
				Boolean adminPageloginStatus = adminLogin.adminPageLoginActions(driver, adminLoginEmail, adminLoginPassword);
				//Check whether admin login credentials valid or not
				if(adminPageloginStatus == true)
				{
					uploadBatchFileActions(driver);
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
	 * Test Case for browsing all transactions
	 * on correct data we can successfully view transaction(s)
	 * Input: WebDriver
	 * Ouptu: Void
	 * @throws InvalidFormatException 
	 */
	public void uploadBatchFileActions(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			int rowCount =xllib.getRowCount("UploadBatch");
			for (int i=1;i<=rowCount;i++)
			{
				financialInstitutionName=xllib.getExcelData("UploadBatch", i, 0);
				batchFileDescription=xllib.getExcelData("UploadBatch", i, 1);
				uploadBatchFileLocation=xllib.getExcelData("UploadBatch", i, 2);
				
				driver.findElement(By.id("module_btn_upload_fi_account_batch")).click();	
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.name("fiid")).clear();
				driver.findElement(By.name("fiid")).sendKeys(financialInstitutionName);
				driver.findElement(By.name("description")).clear();
				driver.findElement(By.name("description")).sendKeys(batchFileDescription);
				driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadBatchFileLocation);
				log.info("Waiting for the batch Upload to complete...");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				boolean present=driver.findElement(By.xpath("//span[text()='Upload failed']")).isDisplayed();
				if(present)
				{
					log.info("Batch File failed to upload");
				}
				else
				{
					log.info("Batch File successfully uploaded");
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.navigate().refresh();
			}
		}
		catch(Exception e)
		{
			log.info("Batch File failed to upload");
		}
	}

}