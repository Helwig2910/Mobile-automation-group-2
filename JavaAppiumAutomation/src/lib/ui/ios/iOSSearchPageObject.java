package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Cancel";
        SEARCH_RESULT_ELEMENT = "xpath:XCUIElementTypeLink";
        SEARCH_EMPTY_RESULTS_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_PLACEHOLDER = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULTS_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]";
        SEARCH_RESULTS_ELEMENTS_BY_TEXT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{TITTLE}']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath:XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_TIP = "xpath://android.widget.TextView[@text='{TIP}']";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
