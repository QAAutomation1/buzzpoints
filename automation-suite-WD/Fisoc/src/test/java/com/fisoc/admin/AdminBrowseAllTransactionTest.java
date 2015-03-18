/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/13/2015
 * @Comments :This automation class will login into admin portal and browse all transaction(s).
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.AdminBrowseAllTransactionTestHelper;
import com.fisoc.util.BaseClass;

public class AdminBrowseAllTransactionTest extends BaseClass
{
	/**
	 * Test Case for searching different Transactions
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	 @Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void adminBrowseAllTransactionsTest() throws InvalidFormatException, InterruptedException
	 {
		 AdminBrowseAllTransactionTestHelper adminHelper = new AdminBrowseAllTransactionTestHelper();
		 adminHelper.browseAllTransactionInitialPage(driver);
	}
}
