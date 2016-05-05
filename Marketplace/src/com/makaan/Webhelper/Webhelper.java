package com.makaan.Webhelper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webhelper {
	
	static WebDriver driver = null;

	public static void  InitiateDriver(){
	   	
		
		
		FirefoxProfile profile = new FirefoxProfile();
		
		
		
		
		profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		profile.setPreference("startup.homepage_welcome_url", "about:blank");
		profile.setPreference("startup.homepage_welcome_url.additional","about:blank");
		profile.setPreference("browser.startup.homepage","about:blank");
		 driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		


	}
	
	public  void GetURL(String URL){
		try {
			System.out.println("opening URL throgh webhelper");
			System.out.println(URL + "url in webhelper");
			driver.get(URL);
			Thread.sleep(4000);
		//	driver.navigate().refresh();
			busyIndicationWait(driver);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	public  void busyIndicationWait(WebDriver driver) throws InterruptedException {

		new WebDriverWait(driver, 50000)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("updating")));
		Thread.sleep(3000);
	}

	public void WaitUntill(String element) {
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));

	}
	public boolean IsElementPresent(String element) {
		try {
			driver.findElement(By.xpath(element)).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	public WebDriver getDriver() {
		return driver;
	}

	public boolean findElementByXpath(String Path) {
		try {
			driver.findElement(By.xpath(Path));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Can not find element by xpath");
			return false;
		}
	}

	public void ClickbyXpath(String webElement) {
		try {
			driver.findElement(By.xpath(webElement)).click();
			System.out.println("inside webhelper click element" +webElement);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Element not Found");
		}
	}
	public void ClickbyLink(String webElement) {
		try {
			driver.findElement(By.linkText(webElement)).click();
			System.out.println("inside webhelper click element by link" +webElement);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Element not Found");
		}
	}
	
	
	public void ClickbyId(String Id) {
		try {
			driver.findElement(By.id(Id)).click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Element not Found");
		}
	}
	public void CloseBrowser() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void enterTextByID(String Path, String Value) {
		try {
			WebElement element = driver.findElement(By.id(Path));
			element.sendKeys(Value);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Element not Found");
		}
	}
	public void enterTextByxpath(String Path, String Value) {
		try {
			WebElement element = driver.findElement(By.xpath(Path));
			element.sendKeys(Value);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("_------------------------Element not Found");
		}
	}
	
	public boolean SwitchWindow(String Path,String VerifyPath){
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		driver.findElement(By.xpath(Path)).click(); // click some link that opens a new window

		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		
		if(driver.findElement(By.xpath(VerifyPath)).isDisplayed()){
			System.out.println("Element is present on Switch Window");
			driver.close(); // close newly opened window when done with it
			driver.switchTo().window(parentHandle); // switch back to the original window
			return true;
		}
			
		return false;
	}
	
	public boolean IsElementPresentbyLink(String element) {
		try {
			driver.findElement(By.linkText(element)).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}

}
}
