import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Ex3 extends CoreTestCase {

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Software QA");

        List<WebElement> list = SearchPageObject.waitForAllResultsPresent();

        assertTrue("Wrong quantity of search results",list.size() > 1);         // сравниваем размер списка результатов поиска с единицей

        SearchPageObject.searchClear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsDisappear();
    }
}