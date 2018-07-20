import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.*;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Ex4 extends CoreTestCase {

    @Test
    public void testWordInSearchResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Pumpkin");
        String tittle = "Pumpkin";

        List<WebElement> elements = SearchPageObject.waitForAllTittlesPresent(tittle);

        for(WebElement element: elements)                         // условие проверки для элементов из списка
        {
                String word = element.getText();
                assertTrue(word.toLowerCase().contains("Pumpkin".toLowerCase()));
                assertTrue(word.contains("Pumpkin"));
        }
    }
}