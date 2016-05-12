package com.makaan.Middleware;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.eclipse.jdt.internal.compiler.ast.MarkerAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.Login;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

public class LoginMiddleware {

	public static Webhelper wb = null;
	public static Login dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public LoginMiddleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public void OpenURL() throws Exception {

		String URL = ReadSheet("Login", "URL", 2);
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		wb.WaitUntill(dict.MakaanLogo);

	}

	public static String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public boolean LoginForm() throws Exception {
		if (wb.IsElementPresent(dict.Login)) {
			return true;

		} else {
			return false;
		}

	}

	public static boolean SocialLogin() throws Exception {
		wb.ClickbyXpath(dict.Login);
		Thread.sleep(2000);
		if (wb.IsElementPresent(dict.facebookLogin) && wb.IsElementPresent(dict.GoogleLoginWindow)) {
			System.out.println("Social login button  pesent");
			if (VerifyFBLogin()) {
			} else
				return false;
			if (VerifyGoogleLogin()) {
			} else
				return false;
		}
		return true;
	}

	public static boolean VerifyFBLogin() throws Exception {
		System.out.println("verifying fb login");

		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath(dict.facebookLogin)).click();
		Thread.sleep(3000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

		}

		if (driver.findElement(By.id(dict.FBUserId)).isDisplayed()) {
			System.out.println("Element is present on Switch Window");
			driver.findElement(By.id(dict.FBUserId)).sendKeys(ReadSheet("Login", "UserName", 2));
			driver.findElement(By.id(dict.FBUserPass)).sendKeys(ReadSheet("Login", "Password", 2));
			driver.findElement(By.xpath(dict.FBLoginButton)).click();
			Thread.sleep(3000);
			/*
			 * if(driver.findElement(By.xpath(dict.FBLoginVerify)).isDisplayed()
			 * ){ //driver.findElement(By.) System.out.println(
			 * "VErified fb login"); }
			 */

			driver.switchTo().window(parentHandle);
			String UserName = ReadSheet("Login", "UserName", 2);
			UserName = UserName.substring(0, 5);
			System.out.println(UserName + " after split");
			String name = driver.findElement(By.xpath(dict.VerifyUserName)).getText().toString();
			System.out.println("user name is: " + name);
			if (driver.findElement(By.xpath(dict.VerifyUserName)).getText().contains(UserName)) {
				System.out.println("Logged in with specfied user");
			}

		} else {
			System.out.println("can not find FB Used Id");
			return false;
		}

		return true;
	}

	public static boolean VerifyGoogleLogin() throws Exception {

		System.out.println("verifying social login");
		MakaanLogout();
		Thread.sleep(3000);
		wb.ClickbyXpath(dict.Login);
		Thread.sleep(2000);
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath(dict.GoogleLoginWindow)).click();
		Thread.sleep(2000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

		}

		if (driver.findElement(By.xpath(dict.GoogleUserId)).isDisplayed()) {
			System.out.println("Element is present on Google form");
			driver.findElement(By.xpath(dict.GoogleUserId)).sendKeys(ReadSheet("Login", "UserName", 3));
			driver.findElement(By.xpath(dict.GoogleNext)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(dict.GooglePass)).sendKeys(ReadSheet("Login", "Password", 3));
			driver.findElement(By.xpath(dict.GoogleLogin)).click();
			Thread.sleep(2000);
			driver.switchTo().window(parentHandle);
			String UserName = ReadSheet("Login", "UserName", 3);
			UserName = UserName.substring(0, 5);
			if (driver.findElement(By.xpath(dict.VerifyUserName)).getText().contains(UserName)) {
				System.out.println("Logged in with specfied user");
			}

		} else {
			System.out.println("can not find Google Used Id");
			return false;
		}
		return true;
	}

	public boolean MakaanLogin() throws Exception {
		System.out.println("inside mkaan login");
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.Login);
		Thread.sleep(2000);
		if (wb.IsElementPresent(dict.BasicLogin)) {
			System.out.println("makaan login button is present on form");
			wb.ClickbyXpath(dict.BasicLogin);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.MakaanForm)) {
				System.out.println("makaan login form is present");
				String Username = ReadSheet("Login", "UserName", 4);
				String Password = ReadSheet("Login", "Password", 4);
				wb.enterTextByID(dict.UserName, Username);
				wb.enterTextByID(dict.Password, Password);
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.BasicLoginButton);
				if (wb.IsElementPresent(dict.VerifyMakaanLogin)) {
					System.out.println("Some error occured while login");
					return false;
				}
				Thread.sleep(2000);
			} else {
				System.out.println("Not able to find makaan form");
				return false;
			}
		} else {
			System.out.println("Basic Login button does not found");
			return false;
		}
		return true;

	}

	public static boolean ForgetPassword() throws Exception {
		System.out.println("inside Forgotpassword in middleware");
		MakaanLogout();
		Thread.sleep(2000);
		wb.ClickbyXpath(dict.Login);
		Thread.sleep(2000);
		
		wb.ClickbyXpath(dict.BasicLogin);
		Thread.sleep(2000);
		if (wb.IsElementPresent(dict.Forgotpassword)) {
			System.out.println("Forgot password button is present on form");
			wb.ClickbyXpath(dict.Forgotpassword);
			Thread.sleep(2000);
			if (wb.getText(dict.ForgetPasswordWindow).equals("reset password")) {
				System.out.println("Reset password windowis present");
				String Username = ReadSheet("Login", "UserName", 4);

				wb.enterTextByxpath(dict.ForgotpasswordInput, Username);
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.ForgetSubmit);
				Thread.sleep(30000);
				if (VerifyEmail(Username)) {
					System.out.println("successfully verify forgot password li nk");
					return true;

				} else {
					return false;
				}
			}

			else {
				System.out.println("Reset password button do not open");
				return false;
			}

		} else {
			System.out.println("Reset password button does not present");
			return false;
		}

	}

	public static boolean VerifyEmail(String data) {
		ResultSet rs = null;
		String id = null;
		String Query = "select * from users where email = '" + data + "' order by id desc limit 1;";
		String Database = "use user";
		try {
			db.Connect();
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getString("id");
				System.out.println("id: " + id);
			}
			Query = "select * from notification_generated where user_id = " + id + " order by id desc limit 1;";
			Database = "use notification";
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				String status = rs.getString("status");
				System.out.println("status: of forgot password mail " + status);
				if (status.equals("Scheduled")) {
					return true;
				} else {
					System.out.println("mail was not sent");
					return false;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return true;
	}

	public static boolean ResetPassword() throws Exception{
		String URL = GetToken();
	//	wb.InitiateDriver();
		wb.GetURL(URL);
		if(wb.IsElementPresentById(dict.Newpassword)){
		wb.enterTextByID(dict.Newpassword,"abcd1234");
		wb.enterTextByID(dict.Confirmpassword, "abcd1234");
		wb.ClickbyXpath(dict.resetPassword);
		Thread.sleep(4000);
		if(wb.IsElementPresent(dict.Login)){
			System.out.println("Reset password Case Passed");
			
			return true;
		}else{
			System.out.println("Some error occured while reseting password");
			return false;
		}
		
		}else{
			System.out.println("URL did not open");
			
			return false;
		}
		
	}
	
	public static String GetToken() throws Exception {
		String data = ReadSheet("Login", "UserName", 4);
	ResultSet rs = null;
	String id = null;
	String URL = null;
	String Query = "select * from users where email = '" + data + "' order by id desc limit 1 ;";
	String Database = "use user";
	try {
		db.Connect();
		rs = db.Execute(Query, Database);
		while (rs.next()) {
			// Retrieve by column name
			id = rs.getString("id");
			System.out.println("id: " + id);
		}
		Query = "select * from forum_user_token where user_id =  " + id+ ";";
		rs = db.Execute(Query, Database);
		while (rs.next()) {
			// Retrieve by column name
			String Token = rs.getString("token");
			System.out.println("token of forgot password mail " + Token);
			 URL = "https://www.makaan.com/reset-password?token=";
			URL= URL.concat(Token);
		}
		
	} catch (SQLException se) {
		se.printStackTrace();
		return null;
	}
	return URL;
}
	
	
	public static boolean MakaanLogout() throws Exception {
		System.out.println("inside MakaanLogout in middleware");

		wb.ClickbyXpath(dict.MenuDrawer);
		Thread.sleep(2000);
		if (wb.IsElementPresentById(dict.Logout)) {
			wb.ClickbyId(dict.Logout);
			Thread.sleep(2000);
			System.out.println("successfully logged out");
			return true;
		} else {
			return false;
		}

	}

	public boolean MakaanSignup() throws Exception {
		System.out.println("inside SignUp in middleware");
		Thread.sleep(2000);
		wb.ClickbyXpath(dict.Login);
		Thread.sleep(4000);
		wb.ClickbyXpath(dict.BasicLogin);
		Thread.sleep(4000);
		if (wb.IsElementPresent(dict.Signup)) {
			System.out.println("Create Acount button is present on form");
			wb.ClickbyXpath(dict.Signup);
			Thread.sleep(2000);
			if (wb.getText(dict.SignupWindow).equals("let the joy begin")) {
				System.out.println("Signup window is present");
				String Username = ReadSheet("Login", "UserName", 3);
				String Pass = ReadSheet("Login", "Password", 3);
				Username = Splitter(Username);
				wb.enterTextByID(dict.SignupName, Username);
				Thread.sleep(1000);
				wb.enterTextByID(dict.SignupEmail, Username);
				Thread.sleep(1000);
				wb.enterTextByID(dict.SignupPassword, Pass);
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.SignupSubmit);
				Thread.sleep(2000);

			} else {
				System.out.println("Signup button do not open");
				return false;
			}

		} else {
			System.out.println("Create account button does not present");
			return false;
		}
		return true;

	}

	public String Splitter(String Value) {
		Random r = new Random();
		int Low = 10;
		int High = 100;
		int Result = r.nextInt(High - Low) + Low;
		String front = Value.substring(0, 13);
		String back = Value.substring(13, 23);
		front = front + "+" + Result + back;
		System.out.println("String " + front);
		return front;

	}

	public static void CloseAll() {
		wb.CloseBrowser();
	}

}
