package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Date;

public class ReportManager {
	private static String path;
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}


	public static ExtentReports createInstance() {
		if (extent == null) {
			path = "\\target\\" + "Report_"+ new Date().getTime() +".html";
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + path);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle("Report");
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName("Automated Tests - Report");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

}
