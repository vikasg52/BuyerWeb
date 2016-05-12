package com.makaan.Webhelper;

import java.util.ArrayList;
import java.util.Random;
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

	public static void InitiateDriver() {

		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		profile.setPreference("startup.homepage_welcome_url", "about:blank");
		profile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
		profile.setPreference("browser.startup.homepage", "about:blank");
		driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public void GetURL(String URL) throws Exception {
			System.out.println("opening URL throgh webhelper");
			System.out.println(URL + " url in webhelper");
			driver.get(URL);
			Thread.sleep(2000);
			driver.navigate().refresh();
			busyIndicationWait(driver);
		}

	

	public void busyIndicationWait(WebDriver driver) throws InterruptedException {

		new WebDriverWait(driver, 50000)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("updating")));
		Thread.sleep(3000);
	}

	public void WaitUntill(String element) {
		try{
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		}catch(Exception e){
			e.printStackTrace();
		
		}

	}

	public boolean IsElementPresent(String element) {
			if(driver.findElement(By.xpath(element)).isDisplayed()){
				System.out.println("Element is present "+element);
			return true;
		} else{
			return false;
		}
	
	}
	
	public boolean IsElementPresentById(String element) {
		if(driver.findElement(By.id(element)).isDisplayed()){
			System.out.println("Element is present "+element);
		return true;
	} else{
		return false;
	}

}
	
	
	public WebDriver getDriver() {
		return driver;
	}

	

	public static void ClickbyXpath(String webElement) {
		driver.findElement(By.xpath(webElement)).click();
		System.out.println("inside webhelper click element" + webElement);
	}

	public static void ClickbyLink(String webElement) {
		try {
			driver.findElement(By.linkText(webElement)).click();
			System.out.println("inside webhelper click element by link" + webElement);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void ClickbyId(String Id) {
		
			driver.findElement(By.id(Id)).click();
		
	}

	public void CloseBrowser() {
			driver.close();
			driver.quit();
	
	}

	public void enterTextByID(String Path, String Value) {
			WebElement element = driver.findElement(By.id(Path));
			element.sendKeys(Value);
	}

	public void enterTextByxpath(String Path,String Value) {
		
			WebElement element = driver.findElement(By.xpath(Path));
			element.sendKeys(Value);
		
	}

	public void findByxpath(String Path) {
		
		WebElement element = driver.findElement(By.xpath(Path));
	
}


	public String getText(String Element) {
		String text = driver.findElement(By.xpath(Element)).getText();
		System.out.println("Text on page throgh get text is: " + text);
		return text;
	}

	public boolean IsElementPresentbyLink(String element) {
		try {
			driver.findElement(By.linkText(element)).isDisplayed();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
