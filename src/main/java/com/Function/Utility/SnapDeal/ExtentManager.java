package com.Function.Utility.SnapDeal;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;

	public static ExtentReports getInstance(){
		
		if(extent == null){
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+".html";
			
			extent = new ExtentReports( System.getProperty("user.dir")+"//Reports//SnapDeal//"+fileName);
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml//"));
			
			extent.addSystemInfo("QA: ","Krishna")
					.addSystemInfo("Firefox Version", "49.0")
					.addSystemInfo("Environment: ","QA");
			
		}
		
		return extent;
		
	}
	
	

}
