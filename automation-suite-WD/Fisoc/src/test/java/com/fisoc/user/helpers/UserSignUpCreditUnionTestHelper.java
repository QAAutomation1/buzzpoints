package com.fisoc.user.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fisoc.constants.TestConstants;
import com.fisoc.util.ExcelLib;

public class UserSignUpCreditUnionTestHelper 
{

	public static String signUpUrl;
	public static String signUpFullName;
	public static String signUpEmail;
	public static String signUpPassword;
	public static String signUpConfirmPassword;
	public static String signUpMemberNo;
	public static String signUpSSN;
	public static String signUpPromoCode;
	public static String financialInstitutionName;
	public static String financialInstitutionUrl;
	public int rowCount;
	private static Logger log = Logger.getLogger(UserSignUpCreditUnionTestHelper.class);
	TestConstants constants = new TestConstants();
	ExcelLib xllib = new ExcelLib();
	

	/**
	 * Test Case for sign up Credit union
	 * on correct data we can successfully sign up for credit union account.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void userSignUpCUActions(WebDriver driver) throws InvalidFormatException
	{
		rowCount = xllib.getRowCount("SignUpCU");
    	for (int i = 1; i <= rowCount; i++) 
    	{
    		try
    		{
    			log.info("Checking on "+i+"th row value");
    			financialInstitutionName = xllib.getExcelData("SignUpCU",i,0);      		  	
    			signUpFullName = xllib.getExcelData("SignUpCU",i,1);
    			signUpEmail = xllib.getExcelData("SignUpCU",i,2);
    			signUpPassword = xllib.getExcelData("SignUpCU",i,3);
    			signUpConfirmPassword = xllib.getExcelData("SignUpCU",i,4);
    			signUpMemberNo = xllib.getExcelData("SignUpCU",i,5);
    			signUpSSN = xllib.getExcelData("SignUpCU",i,6);
    			signUpPromoCode = xllib.getExcelData("SignUpCU",i,7);
    			
    			financialInstitutionUrl = constants.financialInstitutionUrlConvertActions(financialInstitutionName);
    			driver.get(financialInstitutionUrl);
    		  	log.info("Navigating to "+financialInstitutionUrl+"");
    	    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	    	driver.findElement(By.id("signup-trigger")).click();
    	    	log.info("Clicked on SignUp");
    	    	driver.findElement(By.id("name")).clear();
    			driver.findElement(By.id("name")).sendKeys(signUpFullName);
    			driver.findElement(By.id("email")).clear();
    			driver.findElement(By.id("email")).sendKeys(signUpEmail);
    			driver.findElement(By.id("password")).clear();
    			driver.findElement(By.id("password")).sendKeys(signUpPassword);
    			driver.findElement(By.id("passwordRep")).clear();
    			driver.findElement(By.id("passwordRep")).sendKeys(signUpConfirmPassword);    			
    			driver.findElement(By.id("memberNum")).clear();
    			driver.findElement(By.id("memberNum")).sendKeys(signUpMemberNo);
    			driver.findElement(By.id("ssn")).clear();
    			driver.findElement(By.id("ssn")).sendKeys(signUpSSN);
    			driver.findElement(By.id("promoCode")).clear();
    			driver.findElement(By.id("promoCode")).sendKeys(signUpPromoCode);    			
    			driver.findElement(By.id("terms")).click();
    			driver.findElement(By.id("signup-button")).click();

				WebDriverWait wait = new WebDriverWait(driver, 8);
				boolean invalidSignUpData = false;
    			try
    			{
        			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'problem with your registration.')]")));
        			invalidSignUpData = driver.findElement(By.xpath("//h4[contains(text(),'problem with your registration.')]")).isDisplayed();
    			}
    			catch(Exception e)
    			{	}
    	    	if(invalidSignUpData)
    	    	{
    	    		driver.findElement(By.id("signup_alert_close")).click();
    	    		log.info("There was a problem with your registration.");
    	    		Thread.sleep(10000);
    	    	}
    	    	else
    	    	{
    	    		log.info("Successful Sign Up");
    	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verifyHomeAddress_asklater")));
        			driver.findElement(By.id("verifyHomeAddress_asklater")).click();
        	    	log.info("First time signing in to account");
    	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fbphoto")));
        			driver.findElement(By.id("fbphoto")).click();
    	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
        			if(driver.findElement(By.id("logout")).isDisplayed()) 
        			{
        				log.info("Logging out");
        				driver.findElement(By.id("logout")).click();
        				return;
        			}
        			else
        			{
        				return;
        			}
    	    	}
    	    	  	
  	  		}
    		catch (Exception e) 	
    		{ 
    			log.info("Error in test data");
    			e.printStackTrace();
    		}       	  
    	}       	
	}
}
