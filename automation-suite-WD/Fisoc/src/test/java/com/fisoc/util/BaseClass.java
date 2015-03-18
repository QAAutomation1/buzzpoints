/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/16/2015
 * @Comments:This automation class will select the browser and launching an application.
 */
package com.fisoc.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fisoc.constants.TestConstants;

public class BaseClass 
{ 
	private static Logger log = Logger.getLogger(BaseClass.class);
	public WebDriver driver;
	
	/**
	 * Test case to load the necessary driver to run
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 */
	@BeforeTest
	public void launchApplication() throws Exception
	{
		log.info("-------Opening the browser--------");
		driver = DriverManager.getDriverForBrowser(TestConstants.BROWSER_FIREFOX);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * Test case to close the driver 
	 * Input: No parameter
	 * Output: Void
	 */	  
	@AfterTest(groups = { "teardown" })
	public void teardown(){
		log.info("-------Closing the browser--------");
		//Close opened browser
		driver.close();
		//Quit all the opened browsers
		driver.quit();
	}
}
