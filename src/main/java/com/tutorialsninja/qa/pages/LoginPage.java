package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	 WebDriver driver;
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@name='password']")
   private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submit;

	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//Actions
	
	public void enterEmail(String emailText)
	{
		emailAddressField.sendKeys();	
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys();
	}
	
	public void submit()
	{
		submit.click();
	}
	
	

}
