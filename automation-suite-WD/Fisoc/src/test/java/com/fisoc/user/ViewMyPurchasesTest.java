/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/20/2015
 * @Comments :This automation class will login into User Portal and View My Purchases.
 */
package com.fisoc.user;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.user.helpers.ViewMyPurchasesTestHelper;
import com.fisoc.util.BaseClass;

public class ViewMyPurchasesTest extends BaseClass
{
	/**
	 * Test Case for View My Purchases.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "UserSiteRegression"})
	 public void myPurchasesTest() throws InvalidFormatException
	 {
		ViewMyPurchasesTestHelper myPurchases = new ViewMyPurchasesTestHelper();
		myPurchases.viewMyPurchasesInitialPage(driver);
	 }
}
