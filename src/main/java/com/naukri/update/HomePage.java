package com.naukri.update;

import org.openqa.selenium.By;

import com.chetandaulani.core.framework.CustomDriver;
import com.chetandaulani.utilities.BasePage;

public class HomePage extends BasePage {

	public HomePage(CustomDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By loginBy = By.xpath("//a[text()='Login']");
	private By emailBy = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
	private By passwordBy = By.xpath("//input[@placeholder='Enter your password']");
	private By loginBtnBy = By.xpath("//button[text()='Login']");
	

	public HomePage getLoginPop() {
		driver.locateEnabledElement(loginBy).click();
		return this;
	}
	
	public HomePage setEmail(String email) {
		driver.locateEnabledElement(emailBy).sendKeys(email);
		return this;
	}

	public HomePage setPassword(String password) {
		driver.locateEnabledElement(passwordBy).sendKeys(password);
		return this;
	}
	
	public LandingPage goToLandingPagePage() {
		driver.locateEnabledElement(loginBtnBy).click();
		return new LandingPage(driver);
	}

}
