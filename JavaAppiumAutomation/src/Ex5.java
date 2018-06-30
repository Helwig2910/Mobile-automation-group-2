import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex5 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName ","Android");
        capabilities.setCapability("deviceName","AndroidTestDevices");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName ","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void saveTwoArticleToMyFolder()
    {
        waitForElementAndClick(                                                  // жмем на строку поиска на главной
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_line = "Carbon";                                           // задаем поисковый запрос в переменной
        waitForElementAndSendKeys(                                               // вводим поисковый запрос
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(                                                  // жмем на 1 результат выдачи
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Chemical element with the atomic number of 6']"),
                "Cannot find 'Chemical element with the atomic number of 6' topic searching by 'Carbon'",
                5
        );
        WebElement my_article = waitForElementPresent(                                                   // ждем прогрузки статьи и сохраняем элемент в переменную
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title 'Carbon'",
                15
        );

        String my_tittle = my_article.getText();                                            // получаем текст из элемента, сохраненного выше

        waitForElementAndClick(                                                  // жмем на иконку опций
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(                                                   // жмем на опцию из выпадающего списка
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                10
        );
        waitForElementAndClick(                                                  // закрываем попап совета
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClear(                                                  // очищаем поле ввода
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );

        String name_of_folder = "Ex 5";                          // задаем имя папки в переменной

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndClick(                                  // Жмем "ок" в попапе создания папки
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        waitForElementAndClick(                                  // закрываем статью через крестик
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article. cannot find X link",
                5
        );
        waitForElementAndClick(                                                  // жмем на строку поиска на главной
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndClick(                                                  // жмем на "совет" предыдущего поиска
                By.xpath("//android.widget.TextView[@text='Carbon']"),
                "Cannot find 'Carbon' tip of previous search",
                5
        );
        waitForElementAndClick(                                                  // жмем на 2 результат выдачи
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Chemical compound']"),
                "Cannot find 'Chemical compound' topic searching by 'Carbon'",
                5
        );
        waitForElementPresent(                                                   // ждем прогрузки статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title 'Carbon dioxide'",
                15
        );
        waitForElementAndClick(                                                  // жмем на иконку опций
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(                                                   // жмем на опцию из выпадающего списка
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                10
        );
        waitForElementAndClick(                                                   // жмем на созданный нами список
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find 'Ex 5' folder",
                5
        );
        waitForElementAndClick(                                  // закрываем статью через крестик
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article. cannot find X link",
                5
        );
        waitForElementAndClick(                                                    // жмем на иконку списков
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5
        );
        waitForElementAndClick(                                                    // жмем на наш список
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created 'Ex 5' folder",
                10
        );
        swipeElementToLeft(                                                       // удаляем вторую статью
                By.xpath("//*[@text='Carbon dioxide']"),
                "Cannot find saved article with tittle 'Carbon dioxide'"
        );
        waitForElementNotPresent(                                                 // проверяем, что удалилась
                By.xpath("//*[@text='Carbon dioxide']"),
                "'Carbon dioxide' article is still present",
                5
        );
        waitForElementAndClick(                                  // переходим в первую статью в нашем списке
                By.xpath("//android.widget.TextView[@text='Carbon']"),
                "Cannot find 'Carbon' article in saved articles",
                5
        );
        Assert.assertTrue("Wrong article tittle, not 'Carbon'",my_tittle.contains("Carbon"));       // проверяем заголовок статьи
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
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

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes){
                waitForElementPresent(by,"Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
