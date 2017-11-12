package com.Function.Utility.BedBathnBeyond;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Function.Utility.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericKeyword {
	public Properties prop;
	public FileInputStream fileIn;
	public WebDriver driver;
	public ExtentTest test;
	 
	
		public GenericKeyword(ExtentTest test) throws IOException {
			prop = new Properties();
			fileIn = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//Function//Config//BedBathnBeyond//Object.properties");
			//System.out.println(System.getProperty("user.dir"));
			prop.load(fileIn);
			this.test = test;
		}
		
		public void openBrowser(String browserType) {
			if(browserType.equals("Firefox")) {
				driver = new FirefoxDriver();
			}else if (browserType.equals("Chrome")) {
				driver = new ChromeDriver();
			}
			test.log(LogStatus.INFO, "Current browser is "+browserType);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			
			
		}
		public void navigateURL() {
			String URL = prop.getProperty("url");
			driver.get(URL);
			test.log(LogStatus.INFO, "Navigating to URL: "+URL);
		}
		public void closeBrowser() {
			driver.close();
			test.log(LogStatus.INFO, "closing Browser");
		}
		public void click(String object) {
			getElement(object).click();
			test.log(LogStatus.INFO, "Clicking on "+ object);
			
		}
		
		public void searchProduct(String object, String data) {
			getElement(object).sendKeys(data);
			test.log(LogStatus.INFO, "Searching element of "+ data  +" on "+object);
		}
		public WebElement getElement(String object) {
			WebElement webObj = null;
			if(object.endsWith("_xpath")) {
				webObj =  driver.findElement(By.xpath(prop.getProperty(object)));
			}else if(object.endsWith("_id")) {
				webObj =  driver.findElement(By.id(prop.getProperty(object)));
			}	if(object.endsWith("_className")) {
				webObj =  driver.findElement(By.className(prop.getProperty(object)));
			}				
			return webObj;
			
			
		}
		
		public  String isSkip(ExcelReader xls,String testCases) {
			int run= xls.getRowCount("Cases");
			String value = "";
			for(int i =0; i<=run; i++) {
				if(xls.getCellData("Cases", "TCID", i).equalsIgnoreCase(testCases)) {
					value = xls.getCellData("Cases", "RunMode", i);
							
				}
				
			}
			return value;
			
		}
		
		
}
