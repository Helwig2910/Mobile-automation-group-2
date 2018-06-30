import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex6 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName ", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevices");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName ", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testTittlePresent()
    {
        waitForElementAndClick(                                                  // жмем на строку поиска на главной
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_line = "Bagpipes";                                           // задаем поисковый запрос в переменной
        waitForElementAndSendKeys(                                               // вводим поисковый запрос
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(                                                  // жмем на 1 результат выдачи
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Musical instrument']"),
                "Cannot find 'Bagpipes' article",
                5
        );
        assertElementPresent(                                                   // проверяем наличие заголовка без ожидания
                By.id("org.wikipedia:id/view_page_title_text")
        );
    }
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private void assertElementPresent(By by)     // метод проверки находит элемент, предеает его в переменную, забирает из него текст и сравнивает по тексту
    {
        WebElement element = driver.findElement(by);
        String article_title = element.getAttribute("text");
        Assert.assertTrue("Cannot find article tittle 'Bagpipes",article_title.contains("Bagpipes"));
    }
}