package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qautils.Utilities;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Register extends Base {
	public WebDriver driver;
	public Register()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		 driver=   initializeBrowserAndOpenApplication(prop.getProperty("browser"));
			driver.findElement(By.xpath("//a[@title='My Account']")).click();
			driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li[1]")).click();
	}
	@Test(priority=1)
	public void verifyRegisterWithMandatoryFields() {
		// TODO Auto-generated method stub
		
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamps());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys("116629");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	  
		String print=driver.findElement(By.xpath("//div[@id='content']")).getText();
		System.out.println(print);
		//driver.close();
	
	}
	
	@Test(priority=2)
	public void verifyRegisterWithoutMandatoryFields() {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li[1]")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	  
		//FirstName Error
		String actualFirstNameError=driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']")).getText();
		String expectedFirstnameError=dataProp.getProperty("FirstNameError");
		Assert.assertEquals(actualFirstNameError, expectedFirstnameError);
		
		//Last Name Error
		String actualLastNameError=driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']")).getText();
		String expectedLastNameError=dataProp.getProperty("lastNameError");
		Assert.assertEquals(actualLastNameError, expectedLastNameError);
		
		
		//Email address
		String actualEmailError=driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
		String expectedEmailError=dataProp.getProperty("EmailError");
		Assert.assertEquals(actualEmailError, expectedEmailError);
		
		//TelephoneError
		
		String actualTelephoneError=driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText();
		String expectedTelephoneError=dataProp.getProperty("TelephoneError");
		Assert.assertEquals(actualTelephoneError, expectedTelephoneError);
		
		
		//password error
		String actualPasswordError=driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']")).getText();
		String expectedPasswordError=dataProp.getProperty("passwordError");
		Assert.assertEquals(actualTelephoneError, expectedTelephoneError);
		
		//policy error
	/*	String actualPolicyError= driver.findElement(By.xpath("(//div[@class='alert alert-danger alert-dismissible'])[1]")).getText();
		String expectedPolicyError="Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(actualPolicyError, expectedPolicyError); */
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	

}
