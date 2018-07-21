package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITTLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CREATED_LIST_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        HOME_SCREEN_BUTTON;

    /*TEMPLATES METHODS */
    private static String getXpathByNameOfFolder(String name_of_folder)                  // Заменяем заголовок в xpath рез-тов поисковой выдачи на вводимый нами
    {
        return CREATED_LIST_BUTTON.replace("{MY_FOLDER}",name_of_folder);
    }
    /*TEMPLATES METHODS */

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()                              // Метод ожидания заголовка статьи
    {
        return this.waitForElementPresent(TITTLE, "Cannot find article tittle on page",20);
    }

    public WebElement waitForSetTitleElement(String tittle)                              // Метод ожидания заголовка статьи
    {
        return this.waitForElementPresent(tittle, "Cannot find article tittle on page",20);
    }

    public String getArticleTittle()                                    // Метод передачи заголовка статьи в переменную
    {
        WebElement tittle_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return tittle_element.getAttribute("text");
        }
        else{
            return tittle_element.getAttribute("name");
        }
    }

    public void swipeToFooter()                                        // Метод свайпа вниз, пока не достигнем футера
    {
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of article",40);
        }else{
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Cannot find the end of article",40);
        }
    }

    public void addArticleToMyList(String name_of_folder)             // Метод добавления статьи в список, который создается
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToCreatedList(String name_of_folder)                  // Метод добавления статьи в уже существующий список
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        String folder_xpath = getXpathByNameOfFolder(name_of_folder);
        this.waitForElementAndClick(
                folder_xpath,
                "Cannot find 'Ex 5' folder",
                5
        );
    }

    public void closeArticle()                                             // Метод закрытия статьи
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article. cannot find X link",
                5
        );
    }

    public void returnHomeScreen()                                             // Метод закрытия статьи for iOS
    {
        this.waitForElementAndClick(
                HOME_SCREEN_BUTTON,
                "Cannot close article. cannot find W button",
                5
        );
    }

    public void assertTittlePresent(String error_message)                                   // Метод проверки заголовка статьи без ожидания
    {
        this.assertElementPresent(TITTLE, error_message);
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list",20);
    }
}
