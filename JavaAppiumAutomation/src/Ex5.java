import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testSaveTwoArticleToMyFolder()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Carbon");
        SearchPageObject.clickByArticleWithSubstring("Carbon");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String my_title = ArticlePageObject.getArticleTittle();
        String name_of_folder = "Ex 5";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        String tip = "Carbon";
        SearchPageObject.searchClickOnTip(tip);
        SearchPageObject.clickByArticleWithSubstring("Chemical compound");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToCreatedList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        String article_tittle_1 = "Carbon";
        String article_title_2 = "Carbon dioxide";
        MyListsPageObject.swipeByArticleToDelete(article_title_2);
        MyListsPageObject.clickOnArticleInMyList(article_tittle_1);

        assertTrue("Wrong article tittle, not 'Carbon'",my_title.contains("Carbon"));       // проверяем заголовок статьи
    }
}
