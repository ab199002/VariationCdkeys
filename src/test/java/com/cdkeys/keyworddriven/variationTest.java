package com.cdkeys.keyworddriven;

import org.testng.annotations.Test;

import com.cdkeys.variationpom.VariationObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class variationTest 
{
	
	VariationObject vo = new VariationObject();
  
  @BeforeTest
  public void beforeTest() 
  {
	vo.openBrowser("chrome");
	vo.exportPageFactory();
  }
  @Test
  public void varioationTest() throws Exception 
  {
	 vo.openBaseUrl();
	 vo.pause(20);
	 vo.typeValue(vo.txtsearchbox,"overpass");
	 vo.scrollDown();
	 vo.pause(10);
	 vo.btnClick(vo.linkgamename);
	 vo.pause(10);
	 for (int i = 4;i<=36;i++)
	 {
		 vo.handleAlert(vo.alert);
		 vo.btnClick(vo.dropdowncurrency);
		 vo.pause(3);
		 //vo.currencyXpath(i);
		 vo.dropdowncurrencylist.get(i).click();
		 vo.pause(5);
		 String price = vo.returnText(vo.productprice);
		 String tagname = vo.replaceStringValue(1, price);
		 String pricennew = vo.replaceStringValue(2, price);
		 System.out.println(price);
		 System.out.println(pricennew);
		 vo.writeDatatoExcel(i, pricennew);
		 vo.captureScreenshot(tagname);
		 
	 }
	 
  }

  @AfterTest
  public void afterTest() 
  {
	  vo.closeBrowser();
  }

}
