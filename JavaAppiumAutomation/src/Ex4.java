import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Ex4 extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testWordInSearchResult()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Pumpkin");
        String tittle = "Pumpkin";

        List<WebElement> elements = SearchPageObject.waitForAllTittlesPresent(tittle);

        for(WebElement element: elements)                         // условие проверки для элементов из списка
        {
                String word = element.getText();
                Assert.assertTrue(word.toLowerCase().contains("Pumpkin".toLowerCase()));
                Assert.assertTrue(word.contains("Pumpkin"));
        }
    }
}