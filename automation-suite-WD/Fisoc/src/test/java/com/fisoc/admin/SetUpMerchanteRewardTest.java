/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/17/2015
 * @Comments :This automation class will login into admin portal and set up Merchant eReward(s).
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.SetUpMerchanteRewardTestHelper;
import com.fisoc.util.BaseClass;

public class SetUpMerchanteRewardTest extends BaseClass
{
	/**
	 * Test Case for set up Merchant eReward
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void setUpeRewardTest() throws InvalidFormatException, InterruptedException
	 {
		SetUpMerchanteRewardTestHelper eRewardHelper = new SetUpMerchanteRewardTestHelper();
		eRewardHelper.setUpMerchanteRewardInitialPage(driver);
	}
}
