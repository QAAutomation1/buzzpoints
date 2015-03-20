/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/20/2015
 * @Comments :This automation class will login into admin portal and Validate eCaptive Loyalty Card By Web.
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.ValidateeCaptiveLoyaltyCardByWebTestHelper;
import com.fisoc.util.BaseClass;

public class ValidateeCaptiveLoyaltyCardByWebTest extends BaseClass
{
	/**
	 * Test Case for Validate eCaptive Loyalty Card By Web.
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void validateLoyaltyCardByWebTest() throws InvalidFormatException, InterruptedException
	 {
		ValidateeCaptiveLoyaltyCardByWebTestHelper loyaltyCard = new ValidateeCaptiveLoyaltyCardByWebTestHelper();
		loyaltyCard.validateLoyaltyCardByWebInitialPage(driver);
	}
}
