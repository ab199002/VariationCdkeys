package com.cdkeys.variationpom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VariationObject 
{
	static WebDriver w;
	String baseUrl = "https://www.cdkeys.com/";
	static String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jse ;
	FileInputStream fin;
	FileOutputStream fon;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell priceInfo;
	String proPath = System.getProperty("user.dir")+"\\Test.xlsx";
	
	@FindBy(id ="search")
	public WebElement txtsearchbox;
	
	@FindBy(xpath ="//*[@id=\"instant-search-results-container\"]/div/div/ol/li/div/div/div/div[2]/h3/a")
	public WebElement linkgamename;
	
	@FindBy(id ="product-price-5819")
	public WebElement productprice;

	@FindBy(xpath ="//div[@class='header content']//*[@id='switcher-currency-trigger']/strong/span")
	public WebElement dropdowncurrency;
	
	@FindBy(xpath = "//div[@class='header content']//*[@id='switcher-currency']/div/ul/li")
	public List<WebElement> dropdowncurrencylist;
	
	@FindBy(xpath = "//*[@id=\"maincontent\"]/div[3]/div/div[7]/button")
	public WebElement alert;
	
	public void openBrowser(String browser) 
	{

		if (browser.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions c = new ChromeOptions();
			c.addArguments("start-fullscreen");
			w = new ChromeDriver(c);
			
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			w = new FirefoxDriver();
		}

	}
	
	public void closeBrowser()
	{
		w.quit();
	}
	
	public void openBaseUrl()
	{
		w.get(baseUrl);
		//w.manage().window().fullscreen();
	}
	
	public void typeValue(WebElement we, String value)
	{
		we.clear();
		we.sendKeys(value);
	}
	
	
	public void btnClick(WebElement we)
	{
		we.click();
	}
	
	public void handleDropdownlist(WebElement we, String value)
	{
		Select sc = new Select(we);
		sc.selectByValue(value);
	}
	
	public String returnTitle()
	{
		return w.getTitle();
	}
	
	public String returnText(WebElement we)
	{
		return we.getText();
	}
	
	public void pause(int sec)throws Exception {
		sec=sec*1000;
		Thread.sleep(sec);
	}
	
	public void  handleAlert(WebElement we)
	{
		try {
			btnClick(we);
		  }
		  catch(Exception e){
			  
		  }
	}
	
	public void scrollDown()
	{
		jse = (JavascriptExecutor)w;
		jse.executeScript("scroll(0, 250);");
	}
	
	public void  writeDatatoExcel(int rowvalue,String we) throws Exception
	{
		fin = new FileInputStream(proPath);
		wb = new XSSFWorkbook(fin);
		sheet = wb.getSheet("Sheet1");
		row= sheet.createRow(rowvalue);
		priceInfo = row.createCell(0);
		priceInfo.setCellValue(we);
		fon = new FileOutputStream(proPath);
		wb.write(fon);
		fon.close();
	}
	public void captureScreenshot(String filename) throws Exception 
	{	
		TakesScreenshot ts = (TakesScreenshot) w;
		File fin = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fin, new File(projectPath + "\\ScreenShots\\" + filename+".png"));
		
	}
	
	public String replaceStringValue(int status,String replacableString)
	{
		// 1 for replace and 2 for replace all
		String replacedString = null;
		if(status == 1)
		{
			replacedString = replacableString.replace("[0-9]", "");
		}
		else if(status == 2)
		{
			replacedString = replacableString.replaceAll("[A-Za-z$¥₪₹¥₩₱฿₫€£]","");
		}
		return replacedString;
	}
	public void exportPageFactory() 
	{
		PageFactory.initElements(w, this);
	}
	
	public void  currencyXpath(int value)
	{
		w.findElement(By.xpath("//div[@class='header content']//*[@id='switcher-currency']/div/ul/li["+value+"]/a")).click();
	}
}
