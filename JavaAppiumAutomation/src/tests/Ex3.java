package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Ex3 extends CoreTestCase {

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Software QA");

        List<WebElement> list = SearchPageObject.waitForAllResultsPresent("Cannot find elements with 'Linear layout' class");

        assertTrue("Wrong quantity of search results",list.size() > 1);         // сравниваем размер списка результатов поиска с единицей

        SearchPageObject.searchClear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsDisappear();
    }
}