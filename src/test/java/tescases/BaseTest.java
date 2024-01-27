package tescases;

import Models.TestOrder_Model;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import pages.app.Login_Page;


import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.isChrome;
import static pages.PageBase.waitForPageIsLoaded;
import static utils.PropertiesLoader.uploadPropertiesFile;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeSuite
    public void before_BaseTest_Suite() throws Exception {
        baseUrl = uploadPropertiesFile("config.properties").getProperty("baseUrl");
        Configuration.browser = "edge";
        Configuration.browserSize = "1224x718";
//        Configuration.browser = "chrome";
//        String os = System.getProperty("os.name");
//        System.out.println("OS NAME ///");
//        System.out.println(os);
//        System.out.println("///");
//        switch (os) {
//            case "Linux":
//                System.setProperty(
//                        "webdriver.chrome.driver",
//                        "src/main/resources/libs/webdrivers/chromedriver_linux");
//                break;
//
//            case "Windows 10":
//                System.setProperty(
//                        "webdriver.chrome.driver",
//                        "src/main/resources/libs/webdrivers/chromedriver119.exe");
//                break;
//            default:
//                throw new RuntimeException(
//
//                        "Operation System is not defined! Check System.getProperties(\"os.name\")"
//                );
//        }
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        ChromeDriver driver = new ChromeDriver(options);
//        System.out.println(driver.getCapabilities().getCapability("chrome").toString());
        String myos = System.getProperty("os.name");
        System.out.println("OS: "+ myos);
        Configuration.headless = true;
        System.out.println("I am here");
        WebDriverRunner.isHeadless();
        System.setProperty("selenide.reportsFolder", "build/screenshots");
        WebDriverRunner.isFirefox();
        System.out.println( "isHeadles: "+WebDriverRunner.isHeadless());
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
