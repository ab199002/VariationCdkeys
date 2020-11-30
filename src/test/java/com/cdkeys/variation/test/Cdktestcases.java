package com.cdkeys.variation.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Cdktestcases 
{
	String projectpath =System.getProperty("user.dir");//this  will give  project path 
	WebDriver w ;
	JavascriptExecutor jse ;
	FileInputStream fin;
	FileOutputStream fon;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell priceInfo;
	String screenShotPath,tagname;
	String proPath = System.getProperty("user.dir")+"\\Test.xlsx";
	CommonnMethods c = new CommonnMethods();
	
	LinkedList<String> pricelist = new LinkedList<String>();
  @BeforeTest
  public void beforeTest() throws IOException 
  {
	  System.setProperty("webdriver.chrome.driver", projectpath+"\\Drivers\\chromedriver.exe");
	  ChromeOptions c = new ChromeOptions();
	  c.addArguments("start-fullscreen");
	  w= new ChromeDriver(c);//Open Blank  Chrome Browser
	  jse = (JavascriptExecutor)w;
	  
		fin = new FileInputStream(proPath);
		
		wb = new XSSFWorkbook(fin);
		sheet = wb.getSheet("Sheet1");
	  
  }
  
  @Test
  public void variationTest() throws Exception 
  {
	  //WebDriverWait wait = new WebDriverWait(w,30);
	  w.get("https://www.cdkeys.com/");
	  w.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //w.manage().window().fullscreen();//maximize the browser window
	  //Thread.sleep(10000);
	  w.findElement(By.id("search")).sendKeys("overpass");
	 
	  jse.executeScript("scroll(0, 250);");
	  Thread.sleep(10000);
	  w.findElement(By.xpath("//*[@id=\"instant-search-results-container\"]/div/div/ol/li/div/div/div/div[2]/h3/a")).click();
	  Thread.sleep(10000);
	  pricelist.add(w.findElement(By.id("product-price-5819")).getText());
	  System.out.println(pricelist);
	  addPrice();
	 
  }
  
  public void addPrice() throws Exception
  {
	  for (int i = 4;i<=36;i++)
		  
	  {
		  
		  row= sheet.createRow(i);
		  priceInfo = row.createCell(0);
		  w.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  handleexstension();//this will handle  the alert
		  w.findElement(By.xpath("//div[@class='header content']//*[@id='switcher-currency-trigger']/strong/span")).click();
		  Thread.sleep(3000);
		 
		  WebElement CurrencyXPath = w.findElement(By.xpath("//div[@class='header content']//*[@id='switcher-currency']/div/ul/li["+i+"]/a"));
		  System.out.println(tagname);
		  takess();//this will take  sccreenshot 
		  System.out.println(CurrencyXPath);
		  CurrencyXPath.click();
		  w.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
		  String price = w.findElement(By.id("product-price-5819")).getText();
		  tagname = price.replace("[0-9]", "");
		  tagname = tagname+".png";
		  String pricennew =price.replaceAll("[A-Za-z$¥₪₹¥₩₱฿₫€£]","");
		  System.out.println(price);
		  System.out.println(pricennew);
		  pricelist.add(w.findElement(By.id("product-price-5819")).getText());
		  //priceInfo.setCellValue(pricennew);
		  priceInfo.setCellValue(pricennew);
		  fon = new FileOutputStream(proPath);
		  wb.write(fon);
		  fon.close();
//		  System.out.println(pricelist);
	  }
	  System.out.println(pricelist);
  }
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
	  screenShotPath = projectpath+"\\Screenshots\\"+tagname;
	  System.out.println("----------------------------------------");
	  System.out.println(screenShotPath);
	  FileUtils.copyFile(fin, new File(screenShotPath));
  }

  @AfterTest
  public void afterTest() 
  {
	  w.quit();
  }

}
