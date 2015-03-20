/**
 * @author: Prashanth.S
 * @Created Date :02/02/2015
 * @Updated Date :03/13/2015
 * @Comments This class will serve the constants which are used in the whole project
 */
package com.fisoc.constants;

import org.openqa.selenium.WebDriver;

/**
* Method for declaring the constants required for the project
*/
public class TestConstants 
{
	public static final String BROWSER_CHROME = "CH";
	public static final String BROWSER_IE = "IE";
	public static final String BROWSER_FIREFOX="FF";
	public static final String GmailUrl="https://gmail.com/";
	public static final String AdminLoginUrl ="https://qa.fisoc.com/admin2/";
	public static final String HomeUrl="https://qa.fisoc.com/home/";
	public static final String FinancialInstituteUrl="https://qa.fisoc.com/fiName/login.html";
	public static final String InterstitalTypeBYFI="By FI";
	public static final String InterstitalTypeSpecific="Specified Users (User IDs)";
	public static final String InterstitalTypeAllUser="All Users (Global)";
	public static final String Inactive= "INACTIVE";
	public static final String UserCreated="This user has been created! An email has been sent to help the user complete the creation of their account!";
	public static final String GENERIC_PROPERTY_FILE_NAME = "login.properties";
	public static final String SIGN_UP_URL = "signUpURL";
	public static final String PASSWORD = "password";
	public static final String ACCOUNT_NUMBER = "accountNum";
	public static final String SSN = "ssn";
	public static final String JQUERY_REQUIRED_VALIDATION_MESSAGE = "This field is required.";
	public static final String JQUERY_PASSWORD_LENGTH_VALIDATION_MESSAGE = "Please enter at least 6 characters.";
	public static final String JQUERY_TERMS_AND_CONDITION_VALIDATION_MESSAGE = "You must accept the Terms of Service and Privacy Policy to proceed.";
	public static final String LOGIN_URL = "loginURL";
	public static final String LOGIN_EMAIL = "loginEmail";
	public static final String LOGIN_PASSWORD = "loginPassword";
	public static final String INVALID_PASSWORD = "invalidPassword";
	public static final String JQUERY_INVALIDCREDENTIALS = "Please check your username/email and password or reset your password.";
	public static final String REWARD_SAVED = "Reward successfully saved!";
	public static final String MERCHANT_REDEMPTION_VALIDATION_BY_WEB = "Validation was successful!";
	public static final String TRANSACTION_SAVED = "Transaction(s) successfully saved";
	public static final String CHANGES_SAVED = "Changes Saved";
	public static final String E_REWARD = "eReward";
	public static final String E_REWARD_ELIGIBLE = "No";
	public static final String REWARD_SAVE_TYPE_PUBLISH = "Publish";
	public static final String REWARD_SAVE_TYPE_DRAFT = "Save Draft";
	public static final String FI_CLOSE_BUTTON = "Close";
	public static final String NOT_ENOUGH_POINTS_REDEMPTION = "Unable to Process Your Redemption";
	public static final String CREDIT_SUCCESS = "Credit successful";
	public static final String LESS_REWARD_POINTS = "Unable to Process Your Redemption";
	public static final String YES = "Yes";
	public static final int ZERO = 0;
	
	/**
	 * Method for adding Financial Institution Name in Financial Institution Url
	 */
	public String financialInstitutionUrlConvertActions(String financialInstitution)
	{
		String financialInstitutionUrl = "https://qa.fisoc.com/"+financialInstitution+"/login.html";
		return financialInstitutionUrl;
	}
	
	/**
	 * Method for Converting String to Integer.
	 */
	public int convertingStringToInteger(WebDriver driver,String points) {
		
		int pointsValue = Integer.parseInt(points);
		return pointsValue;
	}
}

