/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/19/2015
 * @Comments :This automation class will login into admin portal and Set up Future Change Request.
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.SetUpFutureChangeRequestTestHelper;
import com.fisoc.util.BaseClass;

public class SetUpFutureChangeRequestTest extends BaseClass
{
	/**
	 * Test Case for Change Future Request.
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void futureRequestTest() throws InvalidFormatException, InterruptedException
	 {
		SetUpFutureChangeRequestTestHelper futureRequest = new SetUpFutureChangeRequestTestHelper();
		futureRequest.futureChangeRequestInitialPage(driver);
	}
}
