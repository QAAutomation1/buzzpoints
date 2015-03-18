/**
 * @author: Basappa Hunsikatti
 * @Created Date :03/06/2015
 * @Updated Date :03/16/2015
 * @Comments :This automation class will serve to select the browser and run the admin login
 */
package com.fisoc.admin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.fisoc.admin.helpers.UploadFinancialInstituteBatchFileTestHelper;
import com.fisoc.util.BaseClass;

public class UploadFinancialInstitutionBatchFileTest extends BaseClass
{
	/**
	 * Test Case for uploading the financial institution batch file.
	 * based on the test data inputs.
	 * Input: No parameter
	 * Output: Void
	 * @throws InvalidFormatException, InterruptedException 
	 */	 
	@Test(groups = { "Regression" ,"Functional","Smoke", "AdminSiteRegression"})
	 public void uploadBatchFileTest() throws InvalidFormatException
	 {
		 UploadFinancialInstituteBatchFileTestHelper BatchFileHelper = new UploadFinancialInstituteBatchFileTestHelper();
		 BatchFileHelper.uploadBatchFileInitialPage(driver);
	 }
}
