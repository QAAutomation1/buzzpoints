/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/09/2015
 * @Updated Date :03/20/2015
 * @Comments :This automation class will login into admin portal and Complete eCaptive Loyalty Card Transaction.
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.CompleteeCaptiveLoyaltyCardTransactionTestHelper;
import com.fisoc.util.BaseClass;

public class CompleteeCaptiveLoyaltyCardTransactionTest extends BaseClass
{
	/**
	 * Test Case for Complete eCaptive Loyalty Card Transaction.
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	 @Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void loyaltyCardTransactionTest() throws InvalidFormatException, InterruptedException
	 {
		 CompleteeCaptiveLoyaltyCardTransactionTestHelper creditPointsHelper = new CompleteeCaptiveLoyaltyCardTransactionTestHelper();
		 creditPointsHelper.loyaltyCardTransactionInitialPage(driver);
	}
}
