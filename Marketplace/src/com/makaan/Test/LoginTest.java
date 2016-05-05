package com.makaan.Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.LoginMiddleware;

public class LoginTest {

	LoginMiddleware mw = new LoginMiddleware();


	
	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");
			
			mw.OpenURL();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to Initiate Driver due to exception");
		}

	}

	@Test 
	public void ValidateLoginButton() {
		try {
			System.out.println("Inside Test Validate Login");
			
			mw.LoginForm();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to validate Login button");
		}
		
	}
	
	@Test  (enabled = false)
	public void ValidateSocialLogin() {
		try {
			System.out.println("Inside Test Validate Login");
			
			mw.SocialLogin();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to validate Social login form");
		}
		
	}
	
	@Test
	public void ValidateMakaanLogin() {
		try {
			System.out.println("Inside Test Validate Login");
			
			mw.MakaanLogin();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to validate MakaanLogin form");
		}
		
	}
	
	@AfterClass 
	public void Close(){
		try{
			mw.CloseAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

