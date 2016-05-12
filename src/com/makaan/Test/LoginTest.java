package com.makaan.Test;

import org.testng.Assert;
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

	@Test (priority =1)
	public void ValidateForm() {
		try {
			System.out.println("Inside Test Validate Login");
			
			mw.LoginForm();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to validate CAll Now form");
		}

	}
	
	
	
	
	

}
