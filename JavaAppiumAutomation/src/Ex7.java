import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex7 {

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
        driver.rotate(ScreenOrientation.PORTRAIT);                                                         // предустановка ориентации экрана
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testChangeScreenOrientation()
    {
        waitForElementAndClick(                                                                   // жмем на строку поиска на главной
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_line = "Java";                                                              // задаем поисковый запрос в переменной
        waitForElementAndSendKeys(                                                                // эмулируем ввод запроса с клавиатуры
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(                                                                    // жмем на 2й результат выдачи
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String tittle_before_rotation = waitForElementAndGetAttribute(                              // сохраняем заголовок до изменения ориентации в переменную
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find tittle of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);                                              // поворачиваем экран в "ландшафт"

        String tittle_after_rotation = waitForElementAndGetAttribute(                            // сохраняем заголовок после изменения ориентации в переменную
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find tittle of article",
                15
        );

        Assert.assertEquals(                                                                     // сравниваем заголовки
                "Article tittle have been changed after screen rotation",
                tittle_before_rotation,
                tittle_after_rotation
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
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timoutInSeconds);
        return element.getAttribute(attribute);
    }
}