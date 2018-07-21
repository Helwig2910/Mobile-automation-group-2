package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITTLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITTLE}')]";
        iOS_POPUP_CLOSE = "id:Close";
        SAVED_ARTICLES = "xpath://XCUIElementTypeCollectionView//*[contains(@type,'XCUIElementTypeCell')]";
    }

    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
