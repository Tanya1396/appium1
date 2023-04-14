import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchPageTests extends CoreTestCase {

    SearchPage searchPage = new SearchPage();

    @Test
    void sendKeysRefactored() {
        mainPage.waitForElementAndClick(MainPage.skipButton);
        mainPage.waitForElementAndClick(SearchPage.searchId, MainPage.LONG_TIMEOUT);
        mainPage.waitForElementAndSendKeys(SearchPage.searchFieldId, "appium", MainPage.LONG_TIMEOUT);
        mainPage.waitForElementAndClick(SearchPage.appiumDescriptionId, MainPage.LONG_TIMEOUT);
    }

    @Test
    void checkSearchElementInList(){
        mainPage.waitForElementAndClick(MainPage.skipButton);
        mainPage.waitForElementAndClick(SearchPage.searchId, MainPage.LONG_TIMEOUT);
        mainPage.waitForElementAndSendKeys(SearchPage.searchFieldId, "app", MainPage.LONG_TIMEOUT);
        mainPage.waitForElementPresence(SearchPage.forthListItem, MainPage.SUPER_LONG_TIMEOUT);
        searchPage.swipeUpListUntilFind(SearchPage.searchList, "Cash App", 10, 2500,
                searchPage.getSearchListItemSelector());
        Assertions.assertTrue(searchPage.isHaveDescription(SearchPage.searchDescItem, "Mobile payment service developed by Block, Inc."));
    }

}

