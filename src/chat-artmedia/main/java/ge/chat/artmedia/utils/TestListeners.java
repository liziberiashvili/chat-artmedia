package ge.chat.artmedia.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result){
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
        Utils.logInfo("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        Utils.logPass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        String testName = result.getMethod().getMethodName();
        Utils.logFail("Test failed: " + testName +  " ,full error code: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        Utils.logSkip("Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context){
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context){
        ITestListener.super.onFinish(context);
        ExtentReportManager.flushReports();
    }
}
