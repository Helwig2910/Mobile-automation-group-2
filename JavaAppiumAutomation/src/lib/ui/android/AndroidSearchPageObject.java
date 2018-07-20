package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[contains(@class,'android.widget.LinearLayout')]";
        SEARCH_EMPTY_RESULTS_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_PLACEHOLDER = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULTS_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]";
        SEARCH_RESULTS_ELEMENTS_BY_TEXT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{TITTLE}']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_TIP = "xpath://android.widget.TextView[@text='{TIP}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
