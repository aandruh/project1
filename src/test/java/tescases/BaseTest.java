package tescases;

import Models.TestOrder_Model;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import pages.app.Login_Page;


import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.close;
import static pages.PageBase.waitForPageIsLoaded;
import static utils.PropertiesLoader.uploadPropertiesFile;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeSuite
    public void before_BaseTest_Suite() throws Exception {
        baseUrl = uploadPropertiesFile("config.properties").getProperty("baseUrl");
        String os = System.getProperty("os.name");
        System.out.println(os);
        Configuration.headless = true;
        WebDriverRunner.isHeadless();
//        System.setProperty("selenide.browser", "edge");
//        System.setProperty("selenide.browser", "edge");
        Configuration.browser = "edge";
//        System.setProperty("webdriver.edge.verboseLogging", "true");
        WebDriverRunner.isFirefox();
        System.out.println(WebDriverRunner.isHeadless());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        WebDriverRunner.closeWebDriver();
    }

    @AfterClass
    public void tearDownBaseTest() throws Exception {
        try {
            WebDriverRunner.closeWebDriver();
            close();
        } catch (NoSuchWindowException e) {
            e.printStackTrace();
        }
    }
    public void makerLogin(TestOrder_Model order){
        Login_Page login_page = new Login_Page();
        waitForPageIsLoaded();
        login_page.email_Input.setValue(order.getMakerEmail());
        login_page.password_Input.setValue(order.getMakerPassword());
        waitForPageIsLoaded();
        login_page.continue_Btn.hover().click();
        waitForPageIsLoaded();
    }
}
