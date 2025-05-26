package com.naukri.update;

import org.openqa.selenium.By;

import com.chetandaulani.core.framework.CustomDriver;
import com.chetandaulani.utilities.BasePage;

public class LandingPage extends BasePage {
	public LandingPage(CustomDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By viewProfileBy = By.xpath("//a[@href='/mnjuser/profile' and contains(text(),'View')]");

	public ProfilePage goToProfilePage() {
		driver.locateEnabledElement(viewProfileBy).click();
		return new ProfilePage(driver);
	}
}
