/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/09/2015
 * @Updated Date :03/18/2015
 * @Comments :This automation class will login into admin portal and add Credit Points to Merchant.
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.AddUserCreditPointsTestHelper;
import com.fisoc.util.BaseClass;

public class AddUserCreditPointsTest extends BaseClass
{
	/**
	 * Test Case for adding Credit Points to Merchant
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	 @Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void addUserCreditPointsTest() throws InvalidFormatException, InterruptedException
	 {
		 AddUserCreditPointsTestHelper creditPointsHelper = new AddUserCreditPointsTestHelper();
		 creditPointsHelper.addUserCreditPointsInitialPage(driver);
	}
}
