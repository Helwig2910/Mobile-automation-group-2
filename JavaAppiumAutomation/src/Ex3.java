import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex3 {

    private AppiumDriver driver;                         // объявили переменную драйвера

    @Before                                              // маркер JUnit для выполнения перед каждым тестом
    public void setUp() throws Exception                 // метод установки и настройки аппиума
    {
        DesiredCapabilities capabilities = new DesiredCapabilities(); // задаем капабилити

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); // задали настройку драйвера
    }

    @After                                               // маркер JUnit для выполнения после каждого теста
    public void tearDown()                               // метод выключения драйвера
    {
        driver.quit();                                   // отключили драйвер
    }

    @Test
    public void TestCancelSearch()
    {
        waitForElementAndClick(                          // ищем форму поиска по id и нажимаем на нее
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(                       // ждем оотбражения поля ввода и эмулируем ввод с клавиатуры
                By.id("org.wikipedia:id/search_src_text"),
                "Software QA",
                "Sannot find search input",
                5
        );
        List<WebElement> list = waitForElementsPresent (      // ждем появления элементов определенного класса
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]"),
                "Cannot find elements with class 'LinearLayout'",
                15
        );

        Assert.assertTrue(list.size() > 1);         // сравниваем размер списка результатов поиска с единицей

        waitForElementAndClear(                        // очищаем поле ввода на всякий случай
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(                        // кликаем по крестику
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );
        waitForElementNotPresent(                      // проверяем отсутствие на экране списка результатов поиска
                By.id("org.wikipedia:id/search_results_list"),
                "Search result list is still present",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) // установка метода ожидания и названий параметров
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n"); // текст ошибки, если не найден элемент + перенос на след строку
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)); // условие появления нужного элемента
    }

    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds) // установка метода ожидания многих элементов
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)); // условие появления всех элементов, удовлетворяющих запросу
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) // метод ожидания и нажатия
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) // метод ожидания и ввода с клавиатуры
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) // метод ожидания ОТСУТСТВИЯ элемента
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) // метод ожидания и очистки поля ввода
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}