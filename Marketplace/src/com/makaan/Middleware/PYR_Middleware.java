package com.makaan.Middleware;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Webhelper.*;

import com.makaan.Utilities.*;

public class PYR_Middleware {
	

		static Webhelper wb = null;
		
		public static WebDriver driver;
		public static ExcelOperation ex = null;
		public static ConnectDB db = null;
		public static Xls_Reader xls = null;

		/*
		 * Constructor Calling
		 */
		public PYR_Middleware() {
			wb = new Webhelper();
			ex = new ExcelOperation();
			db = new ConnectDB();
			xls = new Xls_Reader("/home/surabhi/Desktop/Marketplace.xls");
			
			
			System.out.println("inside Middleware Constructor");

		}

		/*
		 * This Function is responsible for Open Url by calling Webhelper Class
		 * object
		 */
		
		public String ReadSheet() throws IOException, NoSuchElementException, TimeoutException{
			List<String> arr = new ArrayList();
			arr = ex.Read_Excel("Files/Marketplace.xls");
			String URL = arr.get(0);
			System.out.println("URL is "+ URL);
			
			return(URL);
		}
		
		/*
		 * This Function is responsible for Open Url by calling Webhelper Class
		 * object
		 */
		
		public void OpenURL() throws NoSuchElementException, TimeoutException, IOException {
			Boolean result = false;
			String URL=	ReadSheet();
			wb.InitiateDriver();
			System.out.println("Opening URL through Middleware");
			wb.GetURL(URL);
			wb.WaitUntill(".//div[@class='makaan-logo']");
			while (result != true) {
				result = wb.IsElementPresent(".//div[@class='makaan-logo']"); /*
									 * wait till some element visibly present on the
									 * page
									 */
				WebDriver driver = wb.getDriver();
				driver.navigate().refresh();
			}

		}
		
		public void DataBaseValidate()throws SQLException{
			
			try{
				String Query = "select * from ENQUIRY limit 5 ;";
			
			String Database = "use proptiger";
			db.Connect();
		 
			db.Execute(Query, Database);
			}catch (SQLException se){
				se.printStackTrace();
			}
		}

		public void VerifyData(){
			String data = xls.getCellData("Sheet1","FullName", 2);
			System.out.println("cell value is " + data);
			
		}


}
