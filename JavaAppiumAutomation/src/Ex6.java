import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.*;

public class Ex6 extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testTittlePresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Bagpipes");
        SearchPageObject.clickByArticleWithSubstring("Musical instrument");

        MainPageObject.assertElementPresent(                                                    // проверяем наличие элемента заголовка без ожидания
                By.id("org.wikipedia:id/view_page_title_text")
        );
    }
}