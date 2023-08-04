package tescases.staging.mail;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.app.CreateNewOrder_Page;
import pages.app.Login_Page;
import pages.app.Orders_Page;
import tescases.BaseTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static pages.PageBase.clickViaJs_01;
import static pages.PageBase.scrollDown;

public class getEmail_Test extends BaseTest {
    @Test
    public void testCopenhagenDistilleryShopGlobal() throws Exception {
//        open("https://google.com");
        open("https://mailtrap.io/register/signin");
        $(".cookies-banner__button").hover().click();
        $("#user_email").hover().click();
        $("#user_email").setValue("alex@bemakers.com");
        $(".mbm.login_next a").hover().click();
        $("#user_password").setValue("superhardPASS");
        $("input[type='submit']").hover().click();
        open("https://mailtrap.io/inboxes/1565036/messages");
        $((By.xpath("//a/span[contains(text(),'11828')]"))).hover().click();

        switchTo().frame($(".i6jjn6"));
        sleep(2000);
        scrollDown();
        clickViaJs_01($("table.email-wrapper tbody tbody tbody a"));









//        Login_Page login_page  = new Login_Page();
//        login_page.email_Input.setValue("alex+andersenwinery@bemakers.com");
//        login_page.password_Input.setValue("Test1111");
//        login_page.continue_Btn.hover().click();
//        Orders_Page orders_page= new Orders_Page(Orders_Page.URL);
//        orders_page.createNewOrder.hover().click();
//        CreateNewOrder_Page createNewOrder_page = new CreateNewOrder_Page(CreateNewOrder_Page.URL);
//        createNewOrder_page.searchForCustomer_Input.hover().click();
//        createNewOrder_page.searchForCustomer_Input.setValue("denmarkcustomer b"); sleep(2000);
//        $(By.xpath("//div[@role='presentation']")).shouldHave() ;
//        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("denmarkcustomer b"), Duration.ofMillis(4000));
//                createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.DOWN);
//        createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.ENTER);
//        $(By.xpath("//header/h2")).shouldHave(Condition.text("denmarkcustomer b"), Duration.ofMillis(4000));
//        createNewOrder_page.next_Btn.hover().click();
//        createNewOrder_page.searchForProduct_Input.hover().click();
//        createNewOrder_page.searchForProduct_Input.setValue("Tarhun"); sleep(2000);
//        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("Tarhun"), Duration.ofMillis(10000));
//        sleep(1000);
//        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.DOWN);
//        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.ENTER);
//        createNewOrder_page.quantity_Input.setValue("1");
//        createNewOrder_page.price_Input.shouldHave(Condition.value("10,00"));
//        createNewOrder_page.priceInclVAR_Label.shouldHave(Condition.text(createNewOrder_page.priceInclVAR_TextLabel+ "12,50" + " DKK"));
//        createNewOrder_page.addToOrder_Btn.hover().click();
//        sleep(2000);
//        createNewOrder_page.next_Btn.hover().click();

    }
}
