package com.naukri.update;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.chetandaulani.browserconfiguration.TestEnvironment;


public class NaukriTest extends TestEnvironment {

    @Parameters({ "username", "password" })
    @Test
    public void updateResume(String username, String password) throws InterruptedException {
        var homePage = new HomePage(getDriver());
        var landingPage = homePage.getLoginPop().setEmail(username).setPassword(password).goToLandingPagePage();
        var profilePage = landingPage.goToProfilePage();
        profilePage.uploadResume("src/test/resources/docs/chetan_daulani.pdf").getLogoutPopup().logout();
    }
}
