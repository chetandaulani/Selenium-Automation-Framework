package com.chetandaulani.core.framework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait extends WebDriverWait {

	private CustomDriver driver;

	public Wait(CustomDriver driver, Duration timeout) {
		super(driver, timeout);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public Element untilElementVisible(By by) {
		super.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.locateElement(by);
	}

	public Element untilElementEnabled(By by) {
		super.until(ExpectedConditions.elementToBeClickable(by));
		return driver.locateElement(by);
	}

}
