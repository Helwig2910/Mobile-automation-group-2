import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex2 extends CoreTestCase {

    @Test                                                // маркер JUnit для тела теста
    public void testSearch()                              // метод теста
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_title = SearchPageObject.getPlaceholderText();              // передаем текст из элемента в переменную

        assertEquals(                                                      // сравниваем текст из элемента с эталоном
                "Wrong placeholder at search field",
                "Search…",
                search_title
        );
    }
}
