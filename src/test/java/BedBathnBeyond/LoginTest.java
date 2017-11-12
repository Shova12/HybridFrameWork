package BedBathnBeyond;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Function.Utility.ExcelReader;
import com.Function.Utility.BedBathnBeyond.GenericKeyword;
import com.Function.Utility.BedBathnBeyond.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest {
	public ExtentReports report;
	public ExtentTest test;
	
	@Test
	public void doLogin() throws IOException {
		
		String testcase = "Login";
		report = ExtentManager.getInstance();
		test = report.startTest("TestCase Name "+testcase);
		GenericKeyword keyword = new GenericKeyword(test);
		
		String workSheet = "TestCase.xlsx";
		ExcelReader xls = new ExcelReader(System.getProperty("user.dir")+"//TestData//BedBathnBeyond//"+workSheet);
		int rowCount = xls.getRowCount("TestCases");
		System.out.println("Row count "+rowCount);
		
		if(keyword.isSkip(xls, testcase).equals("N")) {
			test.log(LogStatus.INFO, "RunMode is set as No");
			throw new SkipException("RunMode is set as NO");	
			
		}
		
		for(int i =0; i<= rowCount; i++) {
			 System.out.println("Count: "+i);
			 if(xls.getCellData("TestCases", "TCID", i).equalsIgnoreCase(testcase)) {
				 System.out.println("Count: "+i);
				 String value = xls.getCellData("TestCases", "Keyword", i);
				 String object = xls.getCellData("TestCases", "Object_ID", i);
				 String data = xls.getCellData("TestCases", "Data", i);
				 
				 if(value.equals("openBrowser")) { 
					 keyword.openBrowser(data);
				 }else if (value.equals("navigateURL")) { 
					 keyword.navigateURL();	
					 
				 }else if (value.equals("click")) { 
					 keyword.click(object);
				 }
			 
			}
		}
		
	}
	
	@AfterTest
	public void afterTest() {
		report.endTest(test);
		report.flush();
	}
}
