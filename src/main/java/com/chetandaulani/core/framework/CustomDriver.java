package com.chetandaulani.core.framework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomDriver implements WebDriver, JavascriptExecutor {

	private WebDriver driver;
	private Wait wait;
	private final int maxWait = 10;

	public CustomDriver(WebDriver driver) {
		this.driver = driver; // Replace with other drivers if needed
		wait = new Wait(this, Duration.ofSeconds(maxWait));
	}

	@Override
	public void get(String url) {
		driver.get(url);
	}

	@Override
	public @Nullable String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public @Nullable String getTitle() {
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	@Override
	public @Nullable String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	@Override
	public Object executeScript(String script, Object... args) {
		if (driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) driver).executeScript(script, args);
		}
		throw new UnsupportedOperationException("Driver does not support JavascriptExecutor");
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		if (driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
		}
		throw new UnsupportedOperationException("Driver does not support JavascriptExecutor");
	}

	public Element locateElement(By locator) {
		WebElement webElement = driver.findElement(locator);
		return new Element(webElement, driver);
	}

	public List<Element> locateElements(By locator) {
		List<WebElement> webElements = driver.findElements(locator);
		List<Element> customElements = new ArrayList<>();
		for (WebElement webElement : webElements)
			customElements.add(new Element(webElement, driver));

		return customElements;
	}

	public Element locateVisibleElement(By by) {
		return wait.untilElementVisible(by);
	}

	public List<WebElement> locateVisibleElements(By by) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public Element locateEnabledElement(By by) {
		return wait.untilElementEnabled(by);
	}

}
