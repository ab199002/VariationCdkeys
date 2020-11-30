package com.cdkeys.variation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonnMethods 
{
	WebDriver w ;
	JavascriptExecutor jse ;
	FileInputStream fin;
	FileOutputStream fon;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell priceInfo;
	String screenShotPath,tagname;
	LinkedList<String> pricelist = new LinkedList<String>();
	String projectpath =System.getProperty("user.dir");
	String proPath = System.getProperty("user.dir")+"\\Test.xlsx";
//	public void addPrice() throws Exception
//	{
//		  for (int i = 4;i<=36;i++)
//			  
//		  {
//			  
//			  row= sheet.createRow(i);
//			  priceInfo = row.createCell(0);
//			  w.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//			  handleexstension();
//			  w.findElement(By.xpath("//div[@class='header content']//*[@id='switcher-currency-trigger']/strong/span")).click();
//			  Thread.sleep(3000);
//			 
//			  WebElement CurrencyXPath = w.findElement(By.xpath("//div[@class='header content']//*[@id='switcher-currency']/div/ul/li["+i+"]/a"));
////			  tagname = CurrencyXPath.getText();//AED
////			  tagname=tagname. replace("\n","");
////			  tagname = tagname+".png";
//			  System.out.println(tagname);
//			  //screenShotPath = projectpath+"\\Screenshots\\"+tagname+".png";
//			  //System.out.println(screenShotPath);
//			  takess();
//			  System.out.println(CurrencyXPath);
//			  CurrencyXPath.click();
//			  w.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			 
//			  String price = w.findElement(By.id("product-price-5819")).getText();
//			  tagname = price.replace("[0-9]", "");
//			  tagname = tagname+".png";
//			  String pricennew =price.replaceAll("[A-Za-z]","");
//			  System.out.println(price);
//			  System.out.println(pricennew);
//			  pricelist.add(w.findElement(By.id("product-price-5819")).getText());
//			  //priceInfo.setCellValue(pricennew);
//			  priceInfo.setCellValue(price);
//			  fon = new FileOutputStream(proPath);
//			  wb.write(fon);
//			  fon.close();
////			  System.out.println(pricelist);
//		  }
//   }
	public void handleexstension()
	  {
		  try {
			  w.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[7]/button")).click();
		  }
		  catch(Exception e){
			  
		  }
		  
	  }
	  
	  public void takess() throws Exception
	  {
		  TakesScreenshot ts  = (TakesScreenshot)w;//create instance of TakeScreenshot interface
		  File fin = ts.getScreenshotAs(OutputType.FILE);
		  //FileUtils.copyFile(fin, new File(screenShotPath));
		  screenShotPath = projectpath+"\\Screenshots\\"+tagname;
		  System.out.println("----------------------------------------");
		  System.out.println(screenShotPath);
		  FileUtils.copyFile(fin, new File(screenShotPath));
	  }

}
	


