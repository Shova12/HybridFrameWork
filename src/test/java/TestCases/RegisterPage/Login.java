package TestCases.RegisterPage;

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

public class Login {
	public ExtentReports report;
	public ExtentTest test;
	
	@Test
	public void doLogin() throws IOException{
		
		String testcase ="Login";
		
		report = ExtentManager.getInstance();
		test = report.startTest(testcase);
		
		String Worksheet = "TestData.xlsx";
		ExcelReader xls = new ExcelReader(System.getProperty("user.dir")+"\\TestData\\"+Worksheet);
		int rowCount = xls.getRowCount("TestCases");
		
		GenericKeyword keyword = new GenericKeyword(test);
		
		if(keyword.isSkip(xls, testcase).equals("N")){
			test.log(LogStatus.INFO, "Run Mode is set as NO");
			throw new SkipException("Run Mode is set as NO");
		}
		
		for(int i=1; i<=rowCount; i++){
			if(xls.getCellData("TestCases", "TCID ", i).equalsIgnoreCase(testcase)){
				String value = xls.getCellData("TestCases", "Keyword", i);
				String object = xls.getCellData("TestCases", "ObjectID", i);
				String data = xls.getCellData("TestCases", "Data", i);
					if(value.equals("openBrowser")){
						keyword.openBrowser(data);	
					}else if(value.equals("navigate")){
				    	keyword.navigate();
					}else if(value.equals("hover")){
					    keyword.hover(object);
					}else if(value.equals("click")){
				    	keyword.click(object);
					}else if(value.equals("insert")){
				    	keyword.insert(object, data);
					}else if(value.equals("switchToIframe")){
				    	keyword.switchToIframe(object);
					}else if(value.equals("readText")){
				    	keyword.readText(object,data);
					}else if(value.equals("compare")){
				    	keyword.compare(object,data);
					}else if(value.equals("clearData")){
				    	keyword.clearData(object);
					}else if(value.equals("alert")){
				    	keyword.alert();
					}
				    	  
			}
		}
		
		
	}
	@AfterTest
	public void afterTest(){
		report.endTest(test);
		report.flush();
	}
		
	

}
