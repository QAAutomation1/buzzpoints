/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/17/2015
 * @Comments :This automation class will login into User Portal and Redeem an eReward.
 */
package com.fisoc.user;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.user.helpers.UserActivityPageTestHelper;
import com.fisoc.util.BaseClass;

public class UserActivityPageTest extends BaseClass
{
	/**
	 * Test Case for user sign up bank.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "UserSiteRegression"})
	 public void redeemeRewardTest() throws InvalidFormatException
	 {
		UserActivityPageTestHelper activity = new UserActivityPageTestHelper();
		activity.userActivityInitialPage(driver);
	 }
}
