import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstTest {
    public long DEFAULT_TIMEOUT = 5;
    public long LONG_TIMEOUT = 10;

    public AndroidDriver<?> driver;

    @BeforeAll
    void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("paltformName", "Android");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("automationName", "UiAutomator2");

        String AppiumURL = "http://0.0.0.0:4723/wd/hub";
        driver = new AndroidDriver<>(new URL(AppiumURL), capabilities);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    void firstTest() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Hi!");
    }

    @Test
    void clickTest() throws InterruptedException {
        Thread.sleep(3000);
        AndroidElement element = (AndroidElement) driver
                .findElementByAndroidUIAutomator("new UiSelector().resourceId(\"org.wikipedia:id/fragment_onboarding_forward_button\")");
        element.click();
        Thread.sleep(3000);
    }

    @Test
    void sendKeysTest() throws InterruptedException {
        Thread.sleep(1000);
        String skipButtonSelector = "new UiSelector().resourceId(\"org.wikipedia:id/fragment_onboarding_skip_button\")";
        AndroidElement skipButton = (AndroidElement) driver.findElementByAndroidUIAutomator(skipButtonSelector);
        skipButton.click();

        String searchSelector = "new UiSelector().resourceId(\"org.wikipedia:id/search_container\")";
        AndroidElement search = (AndroidElement) driver.findElementByAndroidUIAutomator(searchSelector);
        search.click();
        Thread.sleep(1000);

        String searchFieldSelector = "new UiSelector().resourceId(\"org.wikipedia:id/search_src_text\")";
        AndroidElement searchField = (AndroidElement) driver.findElementByAndroidUIAutomator(searchFieldSelector);
        searchField.sendKeys("cat");
        Thread.sleep(1000);
    }

    @Test
    void sendKeysRefactoredTest() throws InterruptedException {
        String skipButtonSelector = "new UiSelector().resourceId(\"org.wikipedia:id/fragment_onboarding_skip_button\")";
        AndroidElement skipButton = (AndroidElement) driver.findElementByAndroidUIAutomator(skipButtonSelector);
        waitForElementAndClick(skipButton);

        String searchSelector = "new UiSelector().resourceId(\"org.wikipedia:id/search_container\")";
        AndroidElement search = (AndroidElement) driver.findElementByAndroidUIAutomator(searchSelector);
        waitForElementAndClick(search);

        String searchFieldSelector = "new UiSelector().resourceId(\"org.wikipedia:id/search_src_text\")";
        AndroidElement searchField = (AndroidElement) driver.findElementByAndroidUIAutomator(searchFieldSelector);
        waitForElementAndSendKeys(searchField, "cat");
        waitForElementAndClear(searchField);
        Thread.sleep(3000);
    }

    @Test
    void clickTestWithoutThreadSleepTest() {
        AndroidElement element = (AndroidElement) driver
                .findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button");
        clickOnElement(element, 3);
        waitForElementAndClick(element);
    }

    @Test
    void checkText() throws InterruptedException {
        String skipButtonSelector = "new UiSelector().resourceId(\"org.wikipedia:id/fragment_onboarding_skip_button\")";
        AndroidElement skipButton = (AndroidElement) driver.findElementByAndroidUIAutomator(skipButtonSelector);
        waitForElementAndClick(skipButton);
        Thread.sleep(3000);
        String customizeButtonSelector = "new UiSelector().resourceId(\"org.wikipedia:id/view_announcement_action_positive\")";
        AndroidElement customizeButton = (AndroidElement) driver.findElementByAndroidUIAutomator(customizeButtonSelector);
        String customizeButtonText = customizeButton.getText();
        Assertions.assertEquals("CUSTOMIZE", customizeButtonText, "Неверное значение атрибута text");
    }

    @Test
    void myTestSearchLanguage() {
        String uiSelectorPattern = "new UiSelector().resourceId(\"%s\")";
        String skipButtonSelector = String.format(uiSelectorPattern, "org.wikipedia:id/fragment_onboarding_skip_button");
        String xpathAddLangLayout = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]";
        String firstLangEngName = String.format(uiSelectorPattern, "org.wikipedia:id/language_subtitle");
        AndroidElement skipButton = (AndroidElement) driver.findElementByAndroidUIAutomator(skipButtonSelector);
        waitForElementAndClick(skipButton);
        waitForElementPreccence("org.wikipedia:id/view_announcement_container", LONG_TIMEOUT);
//        AndroidElement search = (AndroidElement) driver.findElementByAndroidUIAutomator(searchSelector);
        waitForElementAndClick(waitForElementPreccence("org.wikipedia:id/search_container", DEFAULT_TIMEOUT));
        waitForElementPreccence("org.wikipedia:id/add_languages_button", LONG_TIMEOUT);
        AndroidElement langBtn = waitForElementPreccence("org.wikipedia:id/search_lang_button_container", DEFAULT_TIMEOUT);
        waitForElementAndClick(langBtn);
        AndroidElement addLanguge = (AndroidElement) driver.findElementByXPath(xpathAddLangLayout);
        waitForElementAndClick(addLanguge);
        AndroidElement searchLang = waitForElementPreccence("org.wikipedia:id/menu_search_language", DEFAULT_TIMEOUT);
        waitForElementAndClick(searchLang);
        AndroidElement searchTxtInput = waitForElementPreccence("org.wikipedia:id/search_src_text", DEFAULT_TIMEOUT);
        waitForElementAndSendKeys(searchTxtInput, "kor");
        AndroidElement firstFoundLangEngSubTitle = (AndroidElement) driver.findElementByAndroidUIAutomator(firstLangEngName);
        Assertions.assertEquals("Korean", firstFoundLangEngSubTitle.getText());
    }

    public AndroidElement waitForElementPresent(AndroidElement element, long timeout) {
        return (AndroidElement) new WebDriverWait(driver, timeout)
                .withMessage("Элемент " + element + " отсутствует" + "\n")
                .until(ExpectedConditions.visibilityOf(element));
    }

    public AndroidElement waitForElementPreccence(String xpath, long timeout) {
        return (AndroidElement) new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(By.id(xpath)));
    }

    public void clickOnElement(AndroidElement element, long timeout) {
        waitForElementPresent(element, timeout).click();
    }

    public void clickOnElement(AndroidElement element) {
        waitForElementPresent(element, DEFAULT_TIMEOUT).click();
    }

    public void waitForElementAndClick(AndroidElement element, long timeout) {
        waitForElementPresent(element, timeout).click();
    }

    public void waitForElementAndClick(AndroidElement element) {
        waitForElementPresent(element, DEFAULT_TIMEOUT).click();
    }

    public void waitForElementAndClear(AndroidElement element, long timeout) {
        waitForElementPresent(element, timeout).clear();
    }

    public void waitForElementAndClear(AndroidElement element) {
        waitForElementPresent(element, DEFAULT_TIMEOUT).clear();
    }

    public void waitForElementAndSendKeys(AndroidElement element, String keys, long timeout) {
        waitForElementPresent(element, timeout).sendKeys(keys);
    }

    public void waitForElementAndSendKeys(AndroidElement element, String keys) {
        waitForElementPresent(element, DEFAULT_TIMEOUT).sendKeys(keys);
    }
}
