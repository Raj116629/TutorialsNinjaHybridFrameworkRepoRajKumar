package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qautils.Utilities;

public class Login extends Base {
	public WebDriver driver;
	
	public Login()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		
		  
		    driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		    HomePage homepage = new HomePage(driver);
		    homepage.clickOnMyAccountDropMenu();
		    homepage.selectLoginOption();
			
	}
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		LoginPage lp= new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		lp.submit(); 
		
	/*	driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click(); */
		
		
		
	String myAccountActual=	driver.findElement(By.xpath("//a[text()='Edit your account information']")).getText();
	String expected="Edit your account information";
	Assert.assertEquals(myAccountActual,expected);
	//System.out.println(myAccount);
		
		//driver.close();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[] [] supplyTestData()
	{
		Object [][] data= {{"qaraj@gmail.com","116629"},{"pacc12345@gmail.com","116629"}};
		return data;
	}
	
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() 
	{
	
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utilities.generateEmailWithTimeStamps()); // here it will call generateTimeStamps method 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actual=driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		String expected="Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertEquals(actual, expected);
		System.out.println(actual);
		//driver.quit();
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() 
	{
	
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("validEmail")); // here it will call generateTimeStamps method 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actual=driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		String expected=dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actual, expected);
		System.out.println(actual);
		//driver.quit();
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials() 
	{
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(""); // here it will call generateTimeStamps method 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));;
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actual=driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		String expected=dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actual, expected);
		System.out.println(actual);
		//driver.quit();
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utilities.generateEmailWithTimeStamps()); // here it will call generateTimeStamps method 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String actual=driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		String expected=dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actual, expected);
		System.out.println(actual);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
