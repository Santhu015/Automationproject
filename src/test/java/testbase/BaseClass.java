package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;
	public Logger logger;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "sanity", "regression", "master", "datadriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			// OS
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("no matching os");
				return;
			}
			switch (br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default:System.out.println("Invalid browser");
				return;
			}
			//http://192.168.43.12:4444
			//http://LocalHost:4444/wd/hub

			driver= new RemoteWebDriver(new URL("http://192.168.43.12:4444/wd/hub"),capabilities);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appurl"));

	}

	@AfterClass(groups = { "sanity", "regression", "master", "datadriven" })
	public void teardown() {
		driver.quit();
	}

	public String randomstring() {
		String genratedstring = RandomStringUtils.randomAlphabetic(8);
		return genratedstring;
	}

	public String randomnumeric() {
		String genratedstring = RandomStringUtils.randomNumeric(10);
		return genratedstring;
	}

	public String randomalphanumeric() {
		String genratedstring = RandomStringUtils.randomAlphanumeric(8);
		return genratedstring + "@gmail.com";
	}

	public String CaptureScreen(String tname) {

		String timestamp = new SimpleDateFormat("YYYY.MM.dd.HH.MM.ss").format(new Date());
		String targetfilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";

		TakesScreenshot ts = (TakesScreenshot) driver;

		File sourcefile = ts.getScreenshotAs(OutputType.FILE);

		File targetfile = new File(targetfilepath);

		sourcefile.renameTo(targetfile);

		return targetfilepath;

	}

}
