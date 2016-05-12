package com.makaan.Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.makaan.Middleware.SearchMiddleware;

public class SearchTest {

	
	SearchMiddleware smw = new SearchMiddleware();

	@BeforeClass
	public void InitiateDriver() throws Exception {
		System.out.println("Inside Test Initiate Driver");
		try{
			smw.
		}catch(Exception e){
		
		Assert.assertTrue(false, "not able to Initiate Driver due to exception");
		}
	}

	
	
	
}
