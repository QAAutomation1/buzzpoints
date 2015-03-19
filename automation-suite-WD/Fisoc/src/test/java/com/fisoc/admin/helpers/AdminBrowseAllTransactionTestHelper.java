/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/13/2015
 * @Comments This automation class will serve the Admin Login and browse all transaction(s) use cases
 */
package com.fisoc.admin.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.fisoc.util.ExcelLib;

public class AdminBrowseAllTransactionTestHelper 
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
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	private static Logger log = Logger.getLogger(AdminBrowseAllTransactionTestHelper.class);
	
	/**
	 * Test Case for Reading the excel data and login into the admin
	 * portal on successful login we can browse all transaction(s)
	 * Input: WebDriver
	 * Output: Void
	 */
	public void browseAllTransactionInitialPage(WebDriver driver)
	{
		try
		{
			 AdminLoginPageTestHelper adminLogin = new AdminLoginPageTestHelper();
			 rowCount= xllib.getRowCount("AdminLogin");
			 for (int i = 1; i <= rowCount; i++) 
			 {
				adminLoginEmail = xllib.getExcelData("AdminLogin", i, 0);
				adminLoginPassword = xllib.getExcelData("AdminLogin", i, 1); 
				Boolean adminPageloginStatus = adminLogin.adminPageLoginActions(driver, adminLoginEmail, adminLoginPassword);
				//Check whether admin login credentials valid or not
				if(adminPageloginStatus == true)
				{
					browseAllTransactionActions(driver);
					Thread.sleep(3000);
					//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					adminLogin.adminPageLogoutActions(driver);
					
				}
			 }
		}
		catch(Exception e)
		{
			log.info("Error in Initial Page");
		}
	}
	
	/**
	 * Test Case for browsing all transactions
	 * on correct data we can successfully view transaction(s)
	 * Input: WebDriver
	 * Ouptu: Void
	 * @throws InvalidFormatException 
	 */
	public void browseAllTransactionActions(WebDriver driver) throws InvalidFormatException
	{
		rowCount= xllib.getRowCount("BrowseTransactions");
		for (int i = 1; i <= rowCount; i++) 
	   	{
			try
			{
				financialInstitutionSearch=xllib.getExcelData("BrowseTransactions", i, 0);
				financialInstitutionAccountToken=xllib.getExcelData("BrowseTransactions", i, 1);
				transactionDate=xllib.getExcelData("BrowseTransactions", i, 2);
				transactionDescription=xllib.getExcelData("BrowseTransactions", i, 3);
				transactionType=xllib.getExcelData("BrowseTransactions", i, 4);
				transactionStatus=xllib.getExcelData("BrowseTransactions", i, 5);
				transactionBatchId=xllib.getExcelData("BrowseTransactions", i, 6);
				resultsPerPage=xllib.getExcelData("BrowseTransactions", i, 7);
				
				log.info("Browsing "+i+"th transaction");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.id("module_btn_browse_all_transactions")).click();
				
				WebElement financialInstitution = driver.findElement(By.name("fiidfilter"));
				Select selectTransactionType = new Select(financialInstitution);
				selectTransactionType.selectByVisibleText(financialInstitutionSearch);
				
				driver.findElement(By.name("acctnumberfilter")).clear();
				driver.findElement(By.name("acctnumberfilter")).sendKeys(financialInstitutionAccountToken);
				driver.findElement(By.name("sincefilter")).clear();
				driver.findElement(By.name("sincefilter")).sendKeys(transactionDate);
				driver.findElement(By.name("transactionfilter")).clear();
				driver.findElement(By.name("transactionfilter")).sendKeys(transactionDescription);
					   							
				WebElement transactiontype = driver.findElement(By.name("typefilter"));
				Select selecttransactiontype = new Select(transactiontype);
				selecttransactiontype.selectByVisibleText(transactionType);
	   							
				WebElement transactionstatus=driver.findElement(By.name("statusfilter"));
				Select selecttransactionstatus = new Select(transactionstatus);
				selecttransactionstatus.selectByVisibleText(transactionStatus);
	   			
				driver.findElement(By.name("batchidfilter")).clear();
				driver.findElement(By.name("batchidfilter")).sendKeys(transactionBatchId);
	   			
				WebElement resultsperpage=driver.findElement(By.name("limit"));
				Select selectresultsperpage= new Select(resultsperpage);
				selectresultsperpage.selectByVisibleText(resultsPerPage);
	   						
				driver.findElement(By.xpath("//a[@class='btn btn-primary search']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				boolean transactionDetails = driver.findElement(By.xpath("//td[@class='input-mini']")).isDisplayed();
				
				//Check whether transaction is available or not
				if(transactionDetails)
				{				
					log.info("Transaction Search Passed");
				}
				else
				{
					log.info("Transaction Search Failed");
				}
				
				//driver.navigate().refresh();
	   		}
			catch(Exception e)
			{
				log.info("Transaction Search Failed");
			}
	   	}
	}

}