package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    private static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITTLE_TPL = "//*[@text='[TITTLE}']";

    /*TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)                          // Метод замены в xpath названия папки
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getSavedArticleXpathByTittle(String article_tittle)                  // Метод замены в xpath заголовка статьи
    {
        return ARTICLE_BY_TITTLE_TPL.replace("[TITTLE}",article_tittle);
    }
    /*TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)                                     // Метод клика на список
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTittle(String article_tittle)                     // Метод ожидания наличия статьи по заголовку
    {
        String article_xpath = getSavedArticleXpathByTittle(article_tittle);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Saved article still present with tittle " + article_tittle,
                15
        );
    }

    public void waitForArticleToDisappearByTittle(String article_tittle)             // Метод ожидания отсутствия статьи по заголовку
    {
        String article_xpath = getSavedArticleXpathByTittle(article_tittle);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by tittle " + article_tittle,
                15
        );
    }

    public void swipeByArticleToDelete(String article_tittle)                        // Метод свайпа влево для удаления статьи по заголовку
    {
        this.waitForArticleToAppearByTittle(article_tittle);
        String article_xpath = getFolderXpathByName(article_tittle);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTittle(article_tittle);
    }

    public void clickOnArticleInMyList(String article_tittle_1)                     // Метод клика по статье внутри списка
    {
        String article_xpath = getSavedArticleXpathByTittle(article_tittle_1);
        this.waitForElementAndClick(
                By.xpath(article_xpath),
                "Cannot find 'Carbon' article in saved articles",
                5
        );
    }
}
