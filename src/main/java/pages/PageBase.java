package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public abstract class PageBase {

    public static SelenideElement inventory = $(By.xpath("//div//div/h1[contains(text(),'Inventory')]"));
    protected PageBase() {
    }

    protected PageBase(String url) {
        waitForPageIsLoaded();
        //assertEquals(title(), title,
        //        "Expected page title: " + title + " but got: " + title() + ". Expected page url: "+ url + " but got: " + url() + ".");
        assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().startsWith(url),
                "Expected page url starts with: " + url + " but got: " + WebDriverRunner.getWebDriver().getCurrentUrl() + ".");
    }

    public static void waitForPageIsLoaded() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), TimeUnit.MILLISECONDS.toSeconds(Configuration.timeout)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        sleep(4000);
    }

    public static void waitForContainsURL(String url) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), TimeUnit.MILLISECONDS.toSeconds(50000)).until((ExpectedCondition<Boolean>) wd ->
                (getWebDriver().getCurrentUrl().contains(url)));
        sleep(3000);
    }
    public static void scrollUp() {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        jse.executeScript("window.scrollBy(0,-250)", "");
    }
    public static void scrollUp(int rep) {
        for (int i = 0; i < rep; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
            jse.executeScript("window.scrollBy(0,-250)", "");
        }
    }
    public static void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        jse.executeScript("window.scrollBy(0,250)", "");
    }
    public static void clickViaJs_01(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        executor.executeScript("arguments[0].click();", element);
        sleep(500);
        waitForPageIsLoaded();
    }
}
