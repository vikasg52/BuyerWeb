package com.makaan.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.makaan.Middleware.PYR_Middleware;

import org.junit.Ignore;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;


public class City_PYR {

	PYR_Middleware mw = new PYR_Middleware();
	
	@Test(enabled = false)
	public void InitiateDriver() throws NoSuchElementException {
		try {
			System.out.println("Inside Test Initiate Driver");
			
			mw.OpenURL();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to Initiate Driver due to exception");
		}

	}

	
	@Test (enabled = false)
	public void TestDB()
	{
		try{
			System.out.println("Inside Test Connect DB");
			mw.DataBaseValidate();
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to Initiate Driver due to exception");
		}
	}
	
	@Test
	public void ValidateDB()
	{
		try{
			System.out.println("Inside Test VAlidate DB");
			mw.VerifyData();
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to verify due to exception");
		}
	}
	
}
