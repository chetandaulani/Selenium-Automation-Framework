package com.chetandaulani.browserconfiguration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.chetandaulani.core.framework.CustomDriver;
import com.chetandaulani.resources.ConfigReader;

public class TestEnvironment {
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	private WebDriver initializeDriver(String browserName) {
		WebDriver driver;
		switch (browserName.toLowerCase()) {
		case "chrome":
			// Set ChromeOptions for incognito mode
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	@Parameters({ "browser", "application", "environment" }) // This fetches the browser parameter from the TestNG XML
																// file
	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(String browserName, String application, String environment) {
		WebDriver driver = initializeDriver(browserName);
		threadLocalDriver.set(driver);
		goTo(application, environment);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		WebDriver driver = threadLocalDriver.get();
		if (driver != null) {
			driver.quit();
			threadLocalDriver.remove();
		}
	}

	private void goTo(String application, String environment) {
		String url = ConfigReader.getURL(application, environment);
		threadLocalDriver.get().get(url);
	}

	public CustomDriver getDriver() {
		return new CustomDriver(threadLocalDriver.get());
	}

}
