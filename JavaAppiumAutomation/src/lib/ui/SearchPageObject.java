package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String      // используются константы, заданные отдельно для ios и android в соотв. PageObject'ах
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULTS_ELEMENT,
            SEARCH_PLACEHOLDER,
            SEARCH_RESULTS_LIST,
            SEARCH_RESULTS_ELEMENTS,
            SEARCH_RESULTS_ELEMENTS_BY_TEXT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CLEAR_IOS,
            SEARCH_TIP;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getXpathByTittle(String tittle)                  // Заменяем заголовок в xpath рез-тов поисковой выдачи на вводимый нами
    {
        return SEARCH_RESULTS_ELEMENTS_BY_TEXT.replace("{TITTLE}",tittle);
    }

    private static String getResultSearchElement(String substring)        // Заменяем подстроку в xpath на вводимую нами
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getXpathByTip(String tip)        // Заменяем "совет" в xpath на вводимый нами
    {
        return SEARCH_TIP.replace("{TIP}", tip);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput()                                        // Метод "нажимания" на поисковую строку на главной
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
    }

    public WebElement waitForSearchPlaceholder()                       // Метод ожидания плейсхолдера в поисковой строке
    {
        return this.waitForElementPresent(SEARCH_PLACEHOLDER, "Cannot find input field",10);
    }

    public String getPlaceholderText()                                 // Метод получения текста из плейсхолдера
    {
        WebElement plc_text = waitForSearchPlaceholder();
        return plc_text.getAttribute("text");
    }

    public void waitForCancelButtonToAppear()                         // Метод проверки наличия кнопки отмены поиска
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()                     // Метод проверки отсутствия кнопки отмены поиска
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present",5);
    }

    public void waitForSearchResultsDisappear()                     // Метод проверки отсутствия рез-тов поиска (по блокам)
    {
        this.waitForElementNotPresent(SEARCH_RESULTS_LIST, "Search result list is still present",5);
    }

    public void searchClear()                                      // Метод очистки поля поиска
    {
        this.waitForElementAndClear(SEARCH_PLACEHOLDER,"Cannot find search field to clear it",5);
    }

    public void searchClearIOS()                                  // Метод очистки поля поиска в ios
    {
        this.waitForElementAndClick(SEARCH_CLEAR_IOS, "Cannot find ios search clear 'x' button", 5);
    }

    public void clickCancelSearch()                                // Метод клика на кнопку отмены поиска
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)                      // Метод ввода поискового запроса, текст задается в переменной
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input",15);
    }

    public void waitForSearchResult(String substring)                   // Метод проверки корректной подстроки в открытой статье
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)                   // Метод проверки корректной подстроки в открытой статье
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void searchClickOnTip(String tip)                                   // Метод клика на "совет" предыдущего поиска, совет ищем по тексту. заданному нами
    {
        String tip_xpath = getXpathByTip(tip);
        this.waitForElementAndClick(
                tip_xpath,
                "Cannot find 'Carbon' tip of previous search",
                5
        );
    }

    public List<WebElement> waitForAllResultsPresent(String error_message)      // Метод получения всех результатов поиска
    {
        return this.waitForElementsPresent(SEARCH_RESULTS_ELEMENTS, error_message,15);
    }

    public List<WebElement> waitForAllTittlesPresent(String tittle)              // Метод получения всех результатов поиска по заголовку
    {
        String tittle_xpath = getXpathByTittle(tittle);
        return this.waitForElementsPresent(tittle_xpath, "Cannot find elements with class 'LinearLayout'",15);
    }

    public int getAmountOfFoundArticles()                                        // Метод получения количества результатов поискового запроса
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()                                       // Метод ожидания плейхолдера при отсутствии результатов поиска
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULTS_ELEMENT, "Cannot find empty results label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch()                                  // Метод проверки отсутствия рез-тов поиска
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,"We supposed not to find any results");
    }
}
