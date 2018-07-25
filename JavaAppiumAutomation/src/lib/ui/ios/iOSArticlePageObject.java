package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject
{
    static {
        TITTLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CREATED_LIST_BUTTON = "xpath://android.widget.TextView[@text='{MY_FOLDER}']";
        HOME_SCREEN_BUTTON = "id:Wikipedia, return to Explore";
        CLOSE_ARTICLE_BUTTON = "id:Search";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
