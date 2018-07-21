import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex10 extends CoreTestCase {

    @Test
    public void testSaveTwoArticleToMyFolder()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Carbon");
        SearchPageObject.clickByArticleWithSubstring("Chemical element with atomic number 6");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSetTitleElement("Carbon");

        ArticlePageObject.addArticlesToMySaved();
        ArticlePageObject.closeArticle();

        SearchPageObject.clickByArticleWithSubstring("Chemical compound");

        ArticlePageObject.waitForSetTitleElement("Carbon dioxide");
        ArticlePageObject.addArticlesToMySaved();
        ArticlePageObject.returnHomeScreen();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.closeIOSPopup();
        String article_tittle_1 = "Carbon";
        String article_title_2 = "Carbon dioxide";
        MyListsPageObject.swipeByArticleToDelete(article_title_2);
        MyListsPageObject.clickOnArticleInMyList(article_tittle_1);
    }
}
