package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
        MY_LISTS_LINK;   // используются константы, заданные отдельно для ios и android в соотв. PageObject'ах

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()                                       // Метод клика на иконку "Мои списки"
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My List",
                5
        );
    }
}
