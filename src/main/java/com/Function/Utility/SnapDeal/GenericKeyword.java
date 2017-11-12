package com.Function.Utility.SnapDeal;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.Function.Utility.ExcelReader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericKeyword {
	public Properties prop;
	public FileInputStream file;
	public ExtentTest test;
	public WebDriver driver;
	public WebElement webobj;
	
	public GenericKeyword(ExtentTest test) throws IOException{
		 prop = new Properties();
		 //System.out.println("Source"+System.getProperty("user.dir"));
		 file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//Function//Config//SnapDeal//Object.properties");
		 prop.load(file);
		 
		 this.test = test;
		 
		 
	}
	
	public void openBrowser(String browserType){
		if(browserType.equals("Firefox")){
			driver = new FirefoxDriver();
			//test.log(LogStatus.INFO, "Current Browser: "+browserType);
			
		}
		else if(browserType.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\wamp\\www\\driverforSelenium\\chromedriver.exe");
			driver = new ChromeDriver();
			//test.log(LogStatus.INFO, "Current Browser: "+browserType);
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Current Browser: "+ browserType);
	}
	
	public void navigate(){
		driver.get(prop.getProperty("url"));
		test.log(LogStatus.INFO, "Navigating to URL: "+prop.getProperty("url"));
		
		
	}
	
	public void click(String object){
		getElement(object).click();
		test.log(LogStatus.INFO, "Clicking on Object : "+object);
		
			
	}
	
	public void hover(String object){
		Actions action = new Actions(driver);
		action.moveToElement(getElement(object)).build().perform();
		test.log(LogStatus.INFO, "Hovering on "+object);
	}
	
	public void insert(String object, String data){
		getElement(object).sendKeys(data);
		test.log(LogStatus.INFO, "Inserting data from Excel : "+data+" on Object "+object);
		
	}
	public void keyEnter(String object){
		 getElement(object).sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO, "Clicking on Object : "+object);
		
		
	}
	public void alert(){
		driver.switchTo().alert();
	}
	
	public void countList(String object){
		System.out.println("LIST OF SUGGESTIONS");
		WebElement ele = getElement(object);
		
		//List<WebElement> suggestionList = ele.findElements(By.xpath(prop.getProperty(object)));
		List<WebElement> suggestionList = getElements(object);
		for(int i=0; i<suggestionList.size();i++ ){
			String List = suggestionList.get(i).getText();
			
			System.out.println(List);
		}
		test.log(LogStatus.INFO, "Counting list on "+object);
	}
	
	public void readText(String object, String variable){
		System.out.println("The variable from Excel: "+variable);
		
		String var = getElement(object).getText();
		
		System.out.println("The text from web page: "+var);
		
		System.setProperty(variable, var);
		test.log(LogStatus.INFO, "Reading value from: "+ object +" value on Excel "+ variable);
		
	}
	public void clearData(String object){
		getElement(object).clear();
		test.log(LogStatus.INFO, "Clearing data from "+object);
	}

	public void compare(String object, String variable){
		System.out.println("The variable from Excel: "+variable);
		
		String var = System.getProperty(object);
		if(var.equals(variable)){
			System.out.println("PASS");
			test.log(LogStatus.PASS, "Value "+object+" is same as  "+variable);
			
		}else{
			System.out.println("FAILED");
			test.log(LogStatus.FAIL, "Value on "+object+" different than "+variable);
			takeScreenShot();
			
		}
		
	}
	public void verifyElement(String object){
		int result = getElements(object).size();
		System.out.println("Result: "+result);
		if(result >0){
			 //takeScreenShot();
			test.log(LogStatus.PASS, "Element is displayed on Screen "+object);
		}else{
 		   takeScreenShot();
 		   test.log(LogStatus.FAIL, "Element is not displayed on Screen "+object);
 	   }
		
		
	}
	
	public void switchToNewWindow() throws InterruptedException{
    	
    	Set<String> window = driver.getWindowHandles();
    	
    	System.out.println("Size of windows: "+window.size());
    	
    	Thread.sleep(3001);
    	
    	//window = driver.getWindowHandles();
    	for(String windowName: window){
    		System.out.println(driver.getTitle());
			System.out.println(windowName);
    		driver.switchTo().window(windowName);
    		
    	}
    	
    	test.log(LogStatus.INFO, "Switching on New window.");
    	
    }
	public void closeCurrentWindow(){
		driver.close();
	}
	
	public void switchToDefaultWindow() throws InterruptedException{
		String parentWindow = driver.getWindowHandle();
		driver.switchTo().window(parentWindow);
		test.log(LogStatus.INFO, "Returning to main window.");
	}
	
	public void switchToIframe(String object){
		int sizeofFrame = getElements(object).size();
		System.out.println("No of iFrame: "+sizeofFrame);
		driver.switchTo().frame(1);
		test.log(LogStatus.INFO, "Switching on "+ object);
	}
	
	public void chooseProductDetail(String object) throws InterruptedException{
		
			int  productDetail = 0;
			
			switch(productDetail){	
			case 1:
				chooseSize(object);	
				break;
			case 2: 
				chooseColor(object);
				break;
							
			default:
				addToCart(object);
				break;
			}		
			
	}
	
	public void chooseSize(String object){
		
		List<WebElement> sizeLists = getElements(object);
		System.out.println("Size list: "+sizeLists.size());
		/*for(int i=0; i<=sizeLists.size(); i++){
			String List = sizeLists.get(i).getText();
			System.out.println(List);*/
			if(getElement(object).isDisplayed()){
				System.out.println("The element is displayed.");
				getElement(object).click();
				test.log(LogStatus.INFO, object+" is displayed.");
				
			}	
			else{
				System.out.println("Size option is not presence and preceed to next step..");
				test.log(LogStatus.INFO, object+" is not displayed.");
			
			}
		//}
		
		
	}
	public void chooseColor(String object){
		
		if(getElement(object).isDisplayed()){
			System.out.println("The element is displayed.");
			getElement(object).click();
		}	
		else{
			//addToCart(object);
			System.out.println("Colour option is not presence and preceed to next step.");
		}
	}
	
	public void addToCart(String object) throws InterruptedException{
		Thread.sleep(3001);
		getElement(object).click();
		test.log(LogStatus.INFO, "Adding to Cart by click on "+object);
		
		
	}
	
	
	public List<WebElement> getElements(String object){
		List<WebElement> webobjcs = null;
		if(object.endsWith("_xpath")){
			webobjcs = driver.findElements(By.xpath(prop.getProperty(object)));
		}
		
		else if(object.endsWith("_id")){
			webobjcs = driver.findElements(By.id(prop.getProperty(object)));
		}
		
		else if(object.endsWith("_name")){
			webobjcs = driver.findElements(By.name(prop.getProperty(object)));
			
		}
		else if(object.endsWith("_className")){
			webobjcs = driver.findElements(By.className(prop.getProperty(object)));
		}
		return webobjcs;
		
	}
	
	public WebElement getElement(String object){
		if(object.endsWith("_xpath")){
			webobj = driver.findElement(By.xpath(prop.getProperty(object)));
		}
		
		else if(object.endsWith("_id")){
			webobj = driver.findElement(By.id(prop.getProperty(object)));
		}
		
		else if(object.endsWith("_name")){
			webobj = driver.findElement(By.name(prop.getProperty(object)));
			
		}
		else if(object.endsWith("_className")){
			webobj = driver.findElement(By.className(prop.getProperty(object)));
		}
		return webobj;
		
	}
	
	public String isSkip(ExcelReader xls, String testcase ){
		 int run = xls.getRowCount("Cases");
		 String value = "";
		 
		 for(int i=0; i<= run; i++){
			 if(xls.getCellData("Cases", "TCID", i).equalsIgnoreCase(testcase)){
				 value =xls.getCellData("Cases", "RunMode",i);
				 
			 }
		 }
		 
		return value;
		
	}
	
	public void takeScreenShot(){
		Date dd = new Date();
		String screenShotFileName = dd.toString().replace(":", "_").replace(" ", "_")+".png";
		
		System.out.println(screenShotFileName);
		String imagePath = System.getProperty("user.dir")+"//ScreenShots//SnapDeal//"+screenShotFileName;
		
		File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			FileUtils.copyFile(scrFile, new File(imagePath));
			
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		
		test.log(LogStatus.INFO, test.addScreenCapture(imagePath));
		
		
	}
	
	
	
	
	

}
