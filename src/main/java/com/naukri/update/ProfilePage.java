package com.naukri.update;

import java.io.File;
import org.openqa.selenium.By;
import com.chetandaulani.core.framework.CustomDriver;
import com.chetandaulani.utilities.BasePage;

public class ProfilePage extends BasePage {

    public ProfilePage(CustomDriver driver) {
        super(driver);
    }

    private By uploadResumeBy = By.xpath("//input[@id='attachCV']");
    private By successMsgBy = By.xpath("//p[text()='Resume has been successfully uploaded.']");
    private By logoutPopupBy = By.xpath("//img[@alt='naukri user profile img']");
    private By logoutBy = By.xpath("//a[@title='Logout']");

    public ProfilePage uploadResume(String path) throws InterruptedException {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        scrollToLazyLoadElement(uploadResumeBy);
        driver.locateElement(uploadResumeBy).sendKeys(absolutePath);
        return this;
    }

    public boolean isResumeUploaded() {
        return waitForElementToBePresent(successMsgBy);
    }

    public ProfilePage getLogoutPopup() throws InterruptedException {
        Thread.sleep(5000);
        driver.locateEnabledElement(logoutPopupBy).click();
        return this;
    }

    public void logout() {
        driver.locateEnabledElement(logoutBy).click();
        driver.manage().deleteAllCookies();
    }
}
