package report;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import utils.WebDriverManager;

public class TestListener implements ITestListener {

	@Override
	public void onFinish(ITestContext iTestContext) {
		ReportManager.getInstance().flush();
		WebDriverManager.quitBrowser();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		String description = iTestResult.getMethod().getDescription();
		if (iTestResult.getTestName() != null) {
			ReportTestManager.startTest(iTestResult.getTestName(),
					iTestResult.getInstance().getClass().getCanonicalName());
		}else if (description != null)
			ReportTestManager.startTest(iTestResult.getMethod().getMethodName() + "( " + description + ")",
					iTestResult.getInstance().getClass().getCanonicalName());
		else {
			ReportTestManager.startTest(iTestResult.getMethod().getMethodName(),
					iTestResult.getInstance().getClass().getCanonicalName());
		}
		ReportTestManager.getTest().log(Status.PASS, "Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		ReportTestManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		WebDriver webDriver = WebDriverManager.getDriver();

		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		ReportTestManager.getTest().log(Status.FAIL, iTestResult.getThrowable());
		ReportTestManager.getTest().fail("details",
				MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		ReportTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}
}