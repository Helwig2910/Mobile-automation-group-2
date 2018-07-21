import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ex10 extends CoreTestCase {

    @Test
    public void testSaveTwoArticleToMyFolder()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Carbon");
        SearchPageObject.clickByArticleWithSubstring("Chemical element with atomic number 6");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleOneElement();
        String article_title = ArticlePageObject.getArticleOneTittle();
        ArticlePageObject.addArticlesToMySaved();
        ArticlePageObject.closeArticle();

        SearchPageObject.clickByArticleWithSubstring("Chemical compound");

        ArticlePageObject.waitForTitleTwoElement();
        ArticlePageObject.addArticlesToMySaved();
        ArticlePageObject.returnHomeScreen();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.closeIOSPopup();
        MyListsPageObject.swipeByArticleToDelete(article_title);
        List<WebElement> list = MyListsPageObject.waitForAllSavedArticlesPresent("Cannot find saved articles");
        assertTrue("No one saved article present",list.size() == 1);
    }
}
