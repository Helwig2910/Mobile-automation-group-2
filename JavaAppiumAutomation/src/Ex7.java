import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex7 extends CoreTestCase {

    @Test
    public void testChangeScreenOrientation()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String tittle_before_rotation = ArticlePageObject.getArticleTittle();

        this.rotateScreenLandscape();

        String tittle_after_rotation = ArticlePageObject.getArticleTittle();

        assertEquals(
                "Article tittle have been changed after screen rotation",
                tittle_before_rotation,
                tittle_after_rotation
        );
    }
}