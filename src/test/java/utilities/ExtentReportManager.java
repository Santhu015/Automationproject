package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.BaseClass;

public class ExtentReportManager implements ITestListener {

	ExtentSparkReporter sparkreporter;
	ExtentReports extent;
	ExtentTest test;

	String repName;

	public void onStart(ITestContext testcontext) {

		String timestamp = new SimpleDateFormat("YYYY.MM.dd.HH.MM.ss").format(new Date());
		repName = "Test-Report" + timestamp + ".html";
		sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("Submodule", "Customer");
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("EnvironMent", "QA");

		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Broswer", browser);

		List<String> includedgroups = testcontext.getCurrentXmlTest().getIncludedGroups();
		if (!includedgroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedgroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got succefully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgpath = new BaseClass().CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();
		
		String pathExtentReportpath = System.getProperty("user.dir")+"\\reports\\" + repName;
		
		File extentReport= new File(pathExtentReportpath);
		

		try {
			Desktop.getDesktop().browse(extentReport.toURI());

		} catch (Exception e) {

		}

	}

}
