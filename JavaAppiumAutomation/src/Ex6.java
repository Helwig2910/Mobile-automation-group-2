import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex6 extends CoreTestCase {

    @Test
    public void testTittlePresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Bagpipes");
        SearchPageObject.clickByArticleWithSubstring("Musical instrument");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertElementPresent("On article page cannot find tittle text ");
    }
}