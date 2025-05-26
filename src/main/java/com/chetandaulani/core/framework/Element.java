package com.chetandaulani.core.framework;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {
	private WebElement webElement; // The wrapped WebElement
	private final WebDriver driver;

	/**
	 * Constructor to initialize the custom Element class with a WebElement
	 * instance.
	 *
	 * @param webElement the WebElement to wrap
	 */
	public Element(WebElement webElement, WebDriver driver) {
		this.webElement = webElement;
		this.driver = driver;
	}

	// Locate a single child element
	public Element locateElement(By locator) {
		WebElement childElement = webElement.findElement(locator);
		return new Element(childElement, driver);
	}

	// Locate multiple child elements
	public List<Element> locateElements(By locator) {
		List<WebElement> childElements = webElement.findElements(locator);
		List<Element> customElements = new ArrayList<>();
		for (WebElement element : childElements) {
			customElements.add(new Element(element, driver));
		}
		return customElements;
	}

	/**
	 * Delegates all methods to the underlying WebElement.
	 */
	public void click() {
		webElement.click();
	}

	public void sendKeys(CharSequence... keysToSend) {
		webElement.sendKeys(keysToSend);
	}

	public String getText() {
		return webElement.getText();
	}

	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	public boolean isEnabled() {
		return webElement.isEnabled();
	}

	public boolean isSelected() {
		return webElement.isSelected();
	}

	public String getAttribute(String name) {
		return webElement.getAttribute(name);
	}

	public String getCssValue(String propertyName) {
		return webElement.getCssValue(propertyName);
	}

	public String getTagName() {
		return webElement.getTagName();
	}

	public void clear() {
		webElement.clear();
	}

	public void submit() {
		webElement.submit();
	}

	public WebElement getWrappedElement() {
		return webElement;
	}

	/**
	 * Performs a JavaScript click on the wrapped WebElement.
	 */
	public void jsClick() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", webElement);
	}

}
