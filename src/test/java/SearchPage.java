import com.sun.org.apache.xpath.internal.operations.And;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends MainPage {

    public SearchPage() {
        super(driver);
    }

    @AndroidFindBy(id = "org.wikipedia:id/search_container")
    public static AndroidElement search;
    public static By searchId = By.id("org.wikipedia:id/search_container");

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wikipedia:id/search_src_text\")")
    public static AndroidElement searchField;
    public static By searchFieldId = By.id("org.wikipedia:id/search_src_text");

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Appium\")")
    public static AndroidElement appiumDescription;
    public static By appiumDescriptionId = By.xpath("//android.widget.TextView[@text=\"Appium\"]/..");

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wikipedia:id/page_list_item_title\")")
    public static List<AndroidElement> searchList;
    public static By forthListItem = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]");

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wikipedia:id/page_list_item_description\")")
    public static List<AndroidElement> searchDescItem;

    public String getSearchListItemSelector() {
        return "new UiSelector().resourceId(\"org.wikipedia:id/page_list_item_title\")";
    }

    public void swipeUpListUntilFind(List<AndroidElement> elements, String text, int maxSwipes, int timeOfSwipe, String selectorToItem) {
        int alreadySwipe = 0;
//        List<AndroidElement> elements = (List<AndroidElement>) driver.findElement(pathToElements);
        while (elements.stream().noneMatch(el -> el.getText().equals(text))) {
            if (alreadySwipe > maxSwipes) {
                return;
            }
            swipeUp(timeOfSwipe);
            alreadySwipe++;
            elements = (List<AndroidElement>) driver.findElementsByAndroidUIAutomator(selectorToItem);
        }

    }

    public boolean isHaveDescription(List<AndroidElement> descriptions, String expectedText){
        return descriptions.stream().anyMatch(desc -> desc.getText().contains(expectedText));
    }

}
