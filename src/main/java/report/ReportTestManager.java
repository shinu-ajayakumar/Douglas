package report;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportTestManager {

	static ExtentTest test = null;
	static ExtentReports extent = ReportManager.getInstance();

	public static ExtentTest getTest() {
		return  test;
	}
	
	public static ExtentTest startTest(String testName, String className) {
		test = extent.createTest(testName, className);
		test.assignAuthor("Shinu");
		test.assignCategory(className);	
		return test;
	}
}
