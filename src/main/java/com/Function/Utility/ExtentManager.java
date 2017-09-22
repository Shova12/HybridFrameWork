package com.Function.Utility;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;

	public static ExtentReports getInstance(){
		
		if(extent == null){
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+".html";
			
			extent = new ExtentReports( System.getProperty("user.dir")+"\\Reports\\"+fileName);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml\\"));
			
			extent.addSystemInfo("QA: ","Krishna")
					.addSystemInfo("Selenium Version", "2.53")
					.addSystemInfo("Environment: ","QA");
			
		}
		
		return extent;
		
	}
	
	

}
