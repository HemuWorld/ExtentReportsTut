package org.hemuWorld;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import static org.hemuWorld.utilities.CustomCapabilities.invokeBrowser;

/**
 * Hello world!
 */
public class TestExecutionEngine {
    public static WebDriver driver;
    public static String browserName = "chrome";

    @BeforeSuite
    public void projectInitiation() {
        driver = invokeBrowser(browserName);
    }

    @BeforeTest
    public void browserOpening() {
        driver.manage()
                .window()
                .maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
