package wiki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase {
    public static AndroidDriver<?> driver;
    public static MainPage mainPage;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("paltformName", "Android");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("automationName", "UiAutomator2");

        String AppiumURL = "http://0.0.0.0:4723/wd/hub";
        driver = new AndroidDriver<>(new URL(AppiumURL), capabilities);

        mainPage = new MainPage(driver);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
