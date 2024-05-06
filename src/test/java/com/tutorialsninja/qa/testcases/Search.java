package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Search  extends Base{
	public WebDriver driver;
	public Search()
	{
		super();
	}
	@BeforeMethod
	public void setup()
	{
		driver=initializeBrowserAndOpenApplication("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validSearch"));
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
      String actualtext= driver.findElement(By.xpath("//a[text()='HP LP3065']")).getText();
      String expectedText="HP LP3065";
      
     System.out.println(actualtext);
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
	
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidSearch"));
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		String actualProduct=driver.findElement(By.xpath("p[contains(text(),'There is no product that matches the search criteria.')]")).getText();
		String expectedProduct=dataProp.getProperty("abcdr");
		Assert.assertEquals(actualProduct, expectedProduct);
		System.out.println(actualProduct);
		////p[contains(text(),'There is no product that matches the search criteria.')]
	}
	
	@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutProvidingAnyProduct()
	{
		
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		String actualProduct=driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]")).getText();
		String expectedProduct="There is no product that matches the search criteria.";
		Assert.assertEquals(actualProduct, expectedProduct);
		System.out.println(actualProduct);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
