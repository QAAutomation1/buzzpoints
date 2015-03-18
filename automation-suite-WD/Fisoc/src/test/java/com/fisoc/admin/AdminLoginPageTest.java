/**
 * @author: Basappa Hunsikatti
 * @Created Date :02/02/2015
 * @Updated Date :03/13/2015
 * @Comments This automation class will serve the Admin Login use cases
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.fisoc.admin.helpers.AdminLoginPageTestHelper;
import com.fisoc.util.BaseClass;

public class AdminLoginPageTest extends BaseClass
{
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void adminPageLoginTest() throws InvalidFormatException
	 {
		 AdminLoginPageTestHelper adminLogin = new AdminLoginPageTestHelper();
		 adminLogin.adminPageLoginInitialPage(driver);
	}
}
