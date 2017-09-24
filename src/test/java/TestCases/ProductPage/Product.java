package TestCases.ProductPage;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Function.Utility.ExcelReader;
import com.Function.Utility.ExtentManager;
import com.Function.Utility.GenericKeyword;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Product {
	public ExtentTest test=null;
	public ExtentReports report=null;
	
	
	@Test
	public void productFunctionality() throws IOException, InterruptedException{
		String testcase = "product";
		
		 report = ExtentManager.getInstance();
		 test = report.startTest("TestCase name is "+testcase);
		
		String WorkSheet = "TestData.xlsx";
		ExcelReader xls = new ExcelReader(System.getProperty("user.dir")+"\\TestData\\"+WorkSheet);
		int rowCount = xls.getRowCount("TestCases");
		
		GenericKeyword keyword = new GenericKeyword(test);
		
		if(keyword.isSkip(xls, testcase).equals("N")){
			test.log(LogStatus.INFO, "Run Mode is set as No.");
			throw new SkipException("Run Mode is set as No.");
		}
		
		for(int i=1; i<=rowCount; i++){
			if(xls.getCellData("TestCases", "TCID", i).equalsIgnoreCase(testcase)){
				String value = xls.getCellData("TestCases", "Keyword", i);
				String object = xls.getCellData("TestCases", "ObjectID", i);
				String data = xls.getCellData("TestCases", "Data", i);
				
				if(value.equals("openBrowser")){
					keyword.openBrowser(data);	
				}else if(value.equals("navigate")){
			    	  keyword.navigate();
				}else if(value.equals("click")){
			    	  keyword.click(object);
				}else if(value.equals("insert")){
			    	  keyword.insert(object, data);
				}else if(value.equals("countList")){
			    	  keyword.countList(object);
				}else if(value.equals("keyEnter")){
			    	  keyword.keyEnter(object);
				}else if(value.equals("readText")){
			    	  keyword.readText(object, data);
				}else if(value.equals("compare")){
			    	  keyword.compare(object, data);
				}else if(value.equals("verifyElement")){
			    	  keyword.verifyElement(object);
				}else if(value.equals("switchToNewWindow")){
			    	  keyword.switchToNewWindow();
				}else if(value.equals("switchDefaultWindow")){
			    	  keyword.switchToDefaultWindow();
				}else if(value.equals("chooseProductDetail")){
			    	  keyword.chooseProductDetail(object);
				}
				else if(value.equals("closeCurrentWindow")){
					keyword.closeCurrentWindow();
				}
				
			
		}
			
		}
		
		/*report.endTest(test);
		report.flush();*/
	}
	
	@AfterTest
	public void afterTest(){
		report.endTest(test);
		report.flush();
	
	}

}
