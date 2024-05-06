package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qautils.ExtentReporter;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		//System.out.println("Execution of Project Tests started");
		 extentReport=ExtentReporter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
	   ExtentTest extentTest=extentReport.createTest(testName);
	   extentTest.log(Status.INFO,testName+" Started Executing");
	 
		
		//System.out.println(testName+" Started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName= result.getName();
		extentTest.log(Status.PASS,testName+" got successfully executed");
		//System.out.println(testName+" got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName= result.getName();
		WebDriver driver= null;
		
	//	System.out.println("Screenshot taken");
		try {
		  driver=(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath= System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got faild");
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName= result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	
	//need to override listeners
	

}
