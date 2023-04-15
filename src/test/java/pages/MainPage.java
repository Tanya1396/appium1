package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    public static long DEFAUL_TIMEOUT = 3;
    public static long LONG_TIMEOUT = 10;
    public static long SUPER_LONG_TIMEOUT = 20;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"SKIP\")")
    public static AndroidElement skipButton;

    public static AndroidDriver<?> driver;

    public MainPage(AndroidDriver<?> driver) {
        MainPage.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AndroidElement waitForElementPresent(AndroidElement element, long timeout) {
        return (AndroidElement) new WebDriverWait(driver, timeout)
                .withMessage("Элемент " + element + " отсутствует" + "\n")
                .until(ExpectedConditions.visibilityOf(element));
    }

    public AndroidElement waitForElementPresence(By xpath, long timeout) {
        return (AndroidElement) new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    public List<WebElement> waitForElementPresenceList(By xpath, long timeout) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
    }
    @Step("кликаем с ожиданием")
    public void waitForElementAndClick(AndroidElement element, long timeout) {
        waitForElementPresent(element, timeout).click();
    }

    public void waitForElementAndClick(AndroidElement element) {
        waitForElementAndClick(element, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndClick(By path, long timeout1, long timeout2) {
        waitForElementAndClick(waitForElementPresence(path, timeout1), timeout2);
    }

    public void waitForElementAndClick(By path, long presence) {
        waitForElementAndClick(path, presence, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndClick(By path) {
        waitForElementAndClick(path, DEFAUL_TIMEOUT, DEFAUL_TIMEOUT);
    }

    @Step("Очистка поля")
    public void waitForElementAndClear(AndroidElement element, long timeout) {
        waitForElementPresent(element, timeout).clear();
    }

    public void waitForElementAndClear(AndroidElement element) {
        waitForElementAndClear(element, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndClear(By path, long timeout1, long timeout2) {
        waitForElementAndClear(waitForElementPresence(path, timeout1), timeout2);
    }

    public void waitForElementAndClear(By path, long precence) {
        waitForElementAndClear(path, precence, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndClear(By path) {
        waitForElementAndClear(path, DEFAUL_TIMEOUT, DEFAUL_TIMEOUT);
    }
    @Step("вбиваем данные в поле {keys}")
    public void waitForElementAndSendKeys(AndroidElement element, String keys, long timeout) {
        waitForElementPresent(element, timeout).sendKeys(keys);
    }

    public void waitForElementAndSendKeys(AndroidElement element, String keys) {
        waitForElementAndSendKeys(element, keys, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndSendKeys(By path, String keys, long timeout1, long timeout2) {
        waitForElementAndSendKeys(waitForElementPresence(path, timeout1), keys, timeout2);
    }

    public void waitForElementAndSendKeys(By path, String keys, long timeout1) {
        waitForElementAndSendKeys(path, keys, timeout1, DEFAUL_TIMEOUT);
    }

    public void waitForElementAndSendKeys(By path, String keys) {
        waitForElementAndSendKeys(path, keys, DEFAUL_TIMEOUT, DEFAUL_TIMEOUT);
    }

    @Step("Swipe up {swipetime} ms")
    public void swipeUp(int swipetime) {
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.7);
        int end_y = (int) (size.height * 0.1);
        driver.hideKeyboard();
        new TouchAction<>(driver).press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipetime)))
                .moveTo(PointOption.point(x, end_y))
                .release().perform();
    }

    public void swipeRight(int swipetime){

    }

}
