package com.chetandaulani.utilities;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.chetandaulani.core.framework.CustomDriver;

public class BasePage {
	protected CustomDriver driver;

	public BasePage(CustomDriver driver) {
		this.driver = driver;
	}

	public void scrollToLazyLoadElement(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean isElementLoaded = false;

		while (!isElementLoaded) {
			try {
				// Wait for the element to be present in the DOM
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement element = wait.until(driver -> driver.findElement(locator));

				// Scroll to the element
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

				// Verify the element is in the viewport
				boolean isInViewport = (Boolean) js.executeScript("const rect = arguments[0].getBoundingClientRect();"
						+ "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) "
						+ "&& rect.right <= (window.innerWidth || document.documentElement.clientWidth));", element);

				if (isInViewport) {
					isElementLoaded = true; // Element is fully within the viewport
				} else {
					js.executeScript("window.scrollBy(0, 300);"); // Scroll more if not fully visible
				}
			} catch (NoSuchElementException e) {
				// Scroll down the page to load more content
				js.executeScript("window.scrollBy(0, 300);");
			} catch (TimeoutException e) {
				// Handle case where element doesn't appear within the timeout
				System.out.println("Element not found within the timeout.");
				break;
			}
		}
	}

	public boolean waitForElementToDisappear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public boolean waitForElementToBePresent(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.presenceOfElementLocated(by));
	    return true;
	}

	public By ByTemplate(String template, String value) {
		return By.xpath(String.format(template, value));
	}

}
