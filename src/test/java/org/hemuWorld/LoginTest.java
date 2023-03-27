package org.hemuWorld;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class LoginTest extends TestExecutionEngine {
    @Test
    public void simpleTest() {

        String pageTitle = driver.getTitle();
        String newPageTitle = "Google";
        Assert.assertEquals(pageTitle,
                newPageTitle);
    }

    @Test
    public void doLogin() {
        String pageTitle = "Google";
        String newPageTitle = "google";
        Assert.assertEquals(pageTitle,
                newPageTitle);
    }

    @Test
    public void isSkip() {
        throw new SkipException("Skipping the test case");
    }
}
