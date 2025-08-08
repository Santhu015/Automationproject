package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	String rName;

	public void onStart(ITestContext testcontext) {
		String timestamp = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
		rName = "Test-Report" + timestamp + ".html";

		sparkreporter = new ExtentSparkReporter(".//reports//" + rName);
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Functional Testing Report");
		sparkreporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Submodule", "Customer");
		extent.setSystemInfo("username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

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
		test.log(Status.FAIL, result.getName() + " got failed");

		try {
			// capturescrenshot
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
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();

		String pathofextentReport = System.getProperty("user.dir") + "//reports//" + rName;
		
		File extentReport=new File(pathofextentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
