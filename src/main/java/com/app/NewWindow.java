package com.app;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewWindow {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.snapdeal.com/products/computers-printers-scanners?sort=plrty");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
    	//  Single browser
		
       Set<String> window = driver.getWindowHandles();
        
        System.out.println(window.size());
		
		driver.findElement(By.xpath(".//*[@id='636212996013']/div[2]/a/picture/img")).click();
        Thread.sleep(5000l);
        
       // 2 browser 
		//  1 to 2
        
        window = driver.getWindowHandles();
        
       // System.out.println(windowhandle.size());
        
        for (String windowname : window) {
			System.out.println(driver.getTitle());
			System.out.println(windowname);
        	driver.switchTo().window(windowname);
		}
        
		String var = driver.findElement(By.xpath(".//*[@id='productOverview']/div[2]/div/div[1]/div[1]/div[1]/h1")).getText();
		
		System.out.println(var);
		
		driver.switchTo().defaultContent();
	}

}
