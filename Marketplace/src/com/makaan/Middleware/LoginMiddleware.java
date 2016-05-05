package com.makaan.Middleware;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Dictionary.Login;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

public class LoginMiddleware {

	public static Webhelper wb = null;
	public static Login dict = null;
	public static WebDriver driver;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public LoginMiddleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public void OpenURL() throws NoSuchElementException, TimeoutException, IOException {
		Boolean result = false;

		String URL = ReadSheet("CAllNow", "URL", 2);
		// String URL2 = URL.toString();
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		wb.WaitUntill(dict.MakaanLogo);

	}

	public String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public void LoginForm() throws Exception {

		wb.ClickbyXpath(dict.Login);

	}

	public void SocialLogin() throws Exception {
		if (wb.IsElementPresent(dict.Social_Login_validate)) {
			System.out.println("social login present on form");
			if (wb.IsElementPresent(dict.faebookLogin) && wb.IsElementPresent(dict.GoogleLogin)) {
				System.out.println("Facebook login button  pesent");
				System.out.println("Google + login button is present");

			}
			VerifySocialLogin();
		}
	}

	public void VerifySocialLogin() throws Exception {
		System.out.println("verifying Facebook login");
		wb.ClickbyXpath(dict.faebookLogin);
		Thread.sleep(2000);
		boolean result = wb.SwitchWindow(dict.faebookLogin, dict.faebookLoginForm);
		if (result) {
			System.out.println("Login form is pesent on facebook login");
		}
		result = wb.SwitchWindow(dict.GoogleLogin, dict.faebookLoginForm);
		if (result) {
			System.out.println("Login form is pesent on Google + login");
		}

	}

	public void MakaanLogin() throws Exception {
		System.out.println("inside mkaan login");
		if (wb.IsElementPresentbyLink("login with email")) {
			System.out.println("makaan login button is present on form");
			wb.ClickbyLink("login with email");
			Thread.sleep(2000);
			if (wb.findElementByXpath(dict.MakaanForm)) {
				System.out.println("makaan login form is present");
				wb.enterTextByID(dict.UserName, "surabhi.jain@proptiger.com");
				wb.enterTextByID(dict.Password, "abcd1234");
				Thread.sleep(1000);
				wb.ClickbyLink("Login");
				Thread.sleep(2000);
			}
			System.out.println("successfully logged in");
		}else{
			System.out.println("login button does not found");
		}
			
	}

	public void CloseAll() {
		wb.CloseBrowser();
	}

}
