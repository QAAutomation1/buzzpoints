/**
 * @author: Basappa Hunsikatti
 * @Created Date :02/02/2015
 * @Updated Date :03/16/2015
 * @Comments:This automation class will sign up for bank.
 */
package com.fisoc.user;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.fisoc.user.helpers.UserSignUpCreditUnionTestHelper;
import com.fisoc.util.BaseClass;

public class UserSignUpCreditUnionTest extends BaseClass
{
	/**
	 * Test Case for user sign up bank.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "UserSiteRegression"})
	 public void userSignupBankTest() throws InvalidFormatException
	 {
		 UserSignUpCreditUnionTestHelper signUpCU = new UserSignUpCreditUnionTestHelper();
		 signUpCU.userSignUpCUActions(driver);
	 }
}
