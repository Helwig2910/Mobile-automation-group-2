package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[contains(@class,'android.widget.LinearLayout')]",
            SEARCH_EMPTY_RESULTS_ELEMENT = "//*[@text='No results found']",
            SEARCH_PLACEHOLDER = "org.wikipedia:id/search_src_text",
            SEARCH_RESULTS_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULTS_ELEMENTS = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]",
            SEARCH_RESULTS_ELEMENTS_BY_TEXT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{TITTLE}']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_TIP = "//android.widget.TextView[@text='{TIP}']";

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
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click search init element",5);
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element");
    }

    public WebElement waitForSearchPlaceholder()                       // Метод ожидания плейсхолдера в поисковой строке
    {
        return this.waitForElementPresent(By.id(SEARCH_PLACEHOLDER), "Cannot find input field",10);
    }

    public String getPlaceholderText()                                 // Метод получения текста из плейсхолдера
    {
        WebElement plc_text = waitForSearchPlaceholder();
        return plc_text.getAttribute("text");
    }

    public void waitForCancelButtonToAppear()                         // Метод проверки наличия кнопки отмены поиска
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()                     // Метод проверки отсутствия кнопки отмены поиска
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present",5);
    }

    public void waitForSearchResultsDisappear()                     // Метод проверки отсутствия рез-тов поиска (по блокам)
    {
        this.waitForElementNotPresent(By.id(SEARCH_RESULTS_LIST), "Search result list is still present",5);
    }

    public void searchClear()                                      // Метод очистки поля поиска
    {
        this.waitForElementAndClear(By.id(SEARCH_PLACEHOLDER),"Cannot find search field to clear it",5);
    }

    public void clickCancelSearch()                                // Метод клика на кнопку отмены поиска
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)                      // Метод ввода поискового запроса, текст задается в переменной
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String substring)                   // Метод проверки корректной подстроки в открытой статье
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)                   // Метод проверки корректной подстроки в открытой статье
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void searchClickOnTip(String tip)                                   // Метод клика на "совет" предыдущего поиска, совет ищем по тексту. заданному нами
    {
        String tip_xpath = getXpathByTip(tip);
        this.waitForElementAndClick(
                By.xpath(tip_xpath),
                "Cannot find 'Carbon' tip of previous search",
                5
        );
    }

    public List<WebElement> waitForAllResultsPresent()                                    // Метод получения всех результатов поиска
    {
        return this.waitForElementsPresent(By.xpath(SEARCH_RESULTS_ELEMENTS), "Cannot find elements with class 'LinearLayout'",15);
    }

    public List<WebElement> waitForAllTittlesPresent(String tittle)                                    // Метод получения всех результатов поиска по заголовку
    {
        String tittle_xpath = getXpathByTittle(tittle);
        return this.waitForElementsPresent(By.xpath(tittle_xpath), "Cannot find elements with class 'LinearLayout'",15);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "Cannot find empty results label by the request", 15);
    }

    public void assertThreIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We supposed not to find any results");
    }
}
