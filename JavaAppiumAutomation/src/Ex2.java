import io.appium.java_client.AppiumDriver;
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

public class Ex2 {

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

    @Test                                                // маркер JUnit для тела теста
    public void TestSearch()                              // метод теста
    {
       waitForElementAndClick(                            // ищем форму поиска по id и нажимаем на нее
               By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
               5
        );

        WebElement title_element = waitForElementPresent(                            // ищем фоновый текстовый шаблон в поле поиска
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input field",
                10
        );

        String search_title = title_element.getAttribute("text");              // передаем текст из элемента в переменную

        Assert.assertEquals(                                                      // сравниваем текст из элемента с эталоном
                "Wrong placeholder at search field",
                "Search…",
                search_title
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) // установка метода ожидания и названий параметров
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n"); // текст ошибки, если не найден элемент + перенос на след строку
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)); // условие появления нужного элемента
    }

    private WebElement waitForElementPresent(By by, String error_message) // дубль метода, заданного выше, с фикс значением таймаута
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) // установка метода ожидания и нажатия
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds); // вызов метода ожидания, определенного выше
        element.click();
        return element;
    }
}
