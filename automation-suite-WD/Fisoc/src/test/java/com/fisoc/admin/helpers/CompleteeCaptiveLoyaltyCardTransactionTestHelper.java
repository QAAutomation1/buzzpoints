/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/09/2015
 * @Updated Date :03/20/2015
 * @Comments This automation class will login into admin portal and Complete eCaptive Loyalty Card Transaction.
 */
package com.fisoc.admin.helpers;

import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.fisoc.constants.TestConstants;
import com.fisoc.user.helpers.UserActivityPageTestHelper;
import com.fisoc.user.helpers.UserLoginPageTestHelper;
import com.fisoc.util.ExcelLib;

public class CompleteeCaptiveLoyaltyCardTransactionTestHelper 
{
	public static String adminLoginEmail;
	public static String adminLoginPassword;
	public static String financialInstitutionLoginEmail;
	public static Boolean adminPageloginStatus;
	public static String settlementDate;
	public static String transactionDate;
	public static String financialInstitutionAccount;
	public static String amountInCents;
	public static String transactionType;
	public static String transactionDescription;
	public static String howManyTransactions;
	public static String transactionSave;
	Set<String> allWindows;
	static Iterator<String> it;
	static String parent;
	static String child;
	public int rowCount;
	ExcelLib xllib = new ExcelLib();
	UserLoginPageTestHelper userLogout = new UserLoginPageTestHelper();
	UserActivityPageTestHelper activity = new UserActivityPageTestHelper();
	AdminLoginPageTestHelper adminLoginLogout = new AdminLoginPageTestHelper();
	private static Logger log = Logger.getLogger(CompleteeCaptiveLoyaltyCardTransactionTestHelper.class);
	
	/**
	 * Test Case for Reading the excel data and login into the admin
	 * Input: WebDriver
	 * Output: Void
	 */
	public void loyaltyCardTransactionInitialPage(WebDriver driver)
	{
		try
		{
			 rowCount= xllib.getRowCount("AdminLogin");
			 for (int i = 1; i <= rowCount; i++) 
			 {
				adminLoginEmail = xllib.getExcelData("AdminLogin", i, 0);
				adminLoginPassword = xllib.getExcelData("AdminLogin", i, 1); 
				adminPageloginStatus = adminLoginLogout.adminPageLoginActions(driver, adminLoginEmail, adminLoginPassword);
				//Check whether admin login credentials valid or not
				if(adminPageloginStatus == true)
				{
					addNewTransactionActions(driver);
					adminLoginLogout.adminPageLogoutActions(driver);
				}
			 }
		}
		catch(Exception e)
		{
			log.info("Error in Initial Page");
		}
	}
	
	/**
	 * Test Case for add transactions
	 * on correct data we can successfully Complete eCaptive Loyalty Card Transaction.
	 * Input: WebDriver
	 * Ouptu: Void
	 * @throws InvalidFormatException 
	 */
	public void addNewTransactionActions(WebDriver driver) throws InvalidFormatException
	{
		rowCount= xllib.getRowCount("AddTransaction");
		for (int i = 1; i <= rowCount; i++) 
	   	{
			try
			{
				financialInstitutionLoginEmail = xllib.getExcelData("AddTransaction", i, 0);
				settlementDate=xllib.getExcelData("AddTransaction", i, 1);
				transactionDate=xllib.getExcelData("AddTransaction", i, 2);
				financialInstitutionAccount=xllib.getExcelData("AddTransaction", i, 3);
				amountInCents=xllib.getExcelData("AddTransaction", i, 4);
				transactionType=xllib.getExcelData("AddTransaction", i, 5);
				transactionDescription=xllib.getExcelData("AddTransaction", i, 6);
				howManyTransactions=xllib.getExcelData("AddTransaction", i, 7);
				
				driver.findElement(By.id("module_btn_browse_all_lifeusers")).click();
				log.info("Searching for User Account");
				Thread.sleep(3000);
				driver.findElement(By.name("emailfilter")).clear();
				driver.findElement(By.name("emailfilter")).sendKeys(financialInstitutionLoginEmail);
				driver.findElement(By.xpath("//i[@ class='icon-search']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[text()='Details']")).click();
				log.info("Adding new transaction...");
				driver.findElement(By.name("settlementDate")).clear();
				driver.findElement(By.name("settlementDate")).sendKeys(settlementDate);
				driver.findElement(By.name("transactionDate")).clear();
				driver.findElement(By.name("transactionDate")).sendKeys(transactionDate);
				
				WebElement fiAccountValue = driver.findElement(By.name("fiAccount"));
				Select st1= new Select(fiAccountValue);
				st1.selectByIndex(0);
				
				driver.findElement(By.name("amountInCents")).clear();
				driver.findElement(By.name("amountInCents")).sendKeys(amountInCents);
				
				WebElement transactionTypeValue = driver.findElement(By.name("type"));
				Select st2= new Select(transactionTypeValue);
				st2.selectByVisibleText(transactionType);
				
				driver.findElement(By.xpath("//input[@value='Walmart']")).clear();
				driver.findElement(By.xpath("//input[@value='Walmart']")).sendKeys(transactionDescription);
				driver.findElement(By.name("howMany")).clear();
				driver.findElement(By.name("howMany")).sendKeys(howManyTransactions);
				driver.findElement(By.xpath("//button[text()='Add']")).click();
				Thread.sleep(3000);
				addTransactionAlertActions(driver);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='modal-dialog modal-lg']/div[@class='modal-content']/div[@class='modal-header']/button[@class='close']")).click();
				//completeeCaptiveLoyaltyCardMailConfirmation(driver);
	        }
			catch(Exception e)
			{
				log.info("Transaction Search Failed");
			}
	   	}
	}
	
	/**
	 * Method for add Transaction Alert Message
	 * on correct data we can successfully add transaction
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void addTransactionAlertActions(WebDriver driver) {
		
		Alert alert = driver.switchTo().alert();
		String transactionSave = alert.getText();
		alert.accept(); 
		if(transactionSave.equalsIgnoreCase(TestConstants.TRANSACTION_SAVED)){
			log.info("Transaction successfully saved");
		}else{
			log.info("Transaction not saved");
		}
	}
}