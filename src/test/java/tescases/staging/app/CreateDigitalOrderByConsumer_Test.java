package tescases.staging.app;

import Models.TestOrder_Model;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.app.Login_Page;
import pages.app.Orders_Page;
import pages.app.PickingOrderList_Page;
import pages.storefront.Checkout_Page;
import pages.storefront.Product_Page;
import pages.storefront.Shop_Page;
import pages.stripe.Stripe_Page;
import tescases.BaseTest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.*;
import static pages.PageBase.*;

public class CreateDigitalOrderByConsumer_Test extends BaseTest {
    private TestOrder_Model order = new TestOrder_Model();

    @BeforeMethod
    public void setUp() throws Exception {
        order.setMarketCountry("dk");

    }




    @Test
    public void testOrderCreation() throws Exception {


        open("https://maltipoo-dk.staging-bemakers.shop/dk");
        Shop_Page shop_page = new Shop_Page();
        shop_page.ageRestricted_ModalDialog.shouldBe(visible);
        shop_page.confirmAge_Btn.hover().click();


        $(".collections a[href='/dk/collections/events']").hover().click();
        waitForPageIsLoaded();
//        scrollDown();
//        scrollDown();
//        sleep(3000);
        $("a[href='/dk/collections/events/products/true-digital").hover().click();
        Product_Page product_page = new Product_Page();
        $("h1.h1").shouldHave(text("True digital"));
        $("h1+p").shouldHave(text("13,75"));
        $(".p-small.text-font").shouldHave(text("\n" +

                        "Incl. 25% VAT"));
//        $(".product-details.mt-12 p.quote").shouldHave(text("Awarded Best Sparkling Fruitwine at the Danish Wine awards 2022"));
        product_page.addToCart_Btn.hover().click();
//        $("button[type='submit']").hover().click();
        product_page.cartCounter.shouldHave(text("1"));
        $("a[href='/dk/cart']").hover().click();
        $("a[href='/dk/checkout']").hover().click();
        waitForPageIsLoaded();
        Checkout_Page checkout_page = new Checkout_Page();
        checkout_page.emailInputField.setValue("test22@test.ts");
        if(false)//if physical product true
        {
            checkout_page.fullNameInputField.setValue("Danish One Consumer");
            checkout_page.streetHouseInputField.setValue("Kløvermarksvej 70D");
            checkout_page.postCodeInputField.setValue("2300");
            checkout_page.cityInputField.setValue("Copenhagen");
            checkout_page.phoneInputField.setValue("45 45 45 45");
            checkout_page.continue_Btn.hover().click();
        }

        //shipping PostNord - Home
        waitForPageIsLoaded();
        checkout_page.continue_Btn.hover().click();
        //payment Credit Card
        waitForPageIsLoaded();
        if(true){// billing address is required
            checkout_page.fullNameBillingInputField.setValue("Danish One Consumer");
            checkout_page.streetHouseBillingInputField.setValue("Kløvermarksvej 70D");
            checkout_page.postCodeBillingInputField.setValue("2300");
            checkout_page.cityBillingInputField.setValue("Copenhagen");
            checkout_page.phoneBillingInputField.setValue("45 45 45 45");
            checkout_page.continue_Btn.hover().click();
        }
        checkout_page.continue_Btn.hover().click();
        waitForPageIsLoaded();
        Stripe_Page stripe_page = new Stripe_Page();
        stripe_page.cardNumberInputField.setValue("4242 4242 4242 4242");
        stripe_page.cardExpiryInputField.setValue("1224");
        stripe_page.cardCvcInputField.setValue("123");
        stripe_page.billingNameInputField.setValue("Test");
        if(stripe_page.billingZipCodeInputField.isDisplayed()){stripe_page.billingZipCodeInputField.setValue("40202");}
        scrollDown();
        scrollDown();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressTab();
//        sleep(2000);stripe_page.billingNameInputField.pressEnter();
        sleep(2000);

        stripe_page.pay_Btn.hover().click();
        sleep(20000);




        checkout_page.paymentSuccessfulTitle.shouldBe(visible);
        checkout_page.thanksForOrderingTitle.shouldBe(visible);
        //get order number
        open("https://app.staging-bemakers.com/login");




        Login_Page login_page  = new Login_Page();
//        login_page.email_Input.setValue("alex+andersenwinery@bemakers.com");
        login_page.email_Input.setValue("alex+dailydk@bemakers.com");
        login_page.password_Input.setValue("Test1111");
        login_page.continue_Btn.hover().click();
        Orders_Page orders_page= new Orders_Page(Orders_Page.URL);
        String orderNumber;
        orderNumber = orders_page.lastOrderNumber.getText();

        open("https://mailtrap.io/signin");
        $(".cookies-banner__button").hover().click();
        $("#user_email").hover().click();
        $("#user_email").setValue("alex@bemakers.com");
        $(".mbm.login_next a").hover().click();
        $("#user_password").setValue("Franklin6064");
        $("input[type='submit']").hover().click();
        open("https://mailtrap.io/inboxes/1565036/messages");

        $((By.xpath("//a/span[contains(text(),'" + orderNumber + "')]"))).hover().click();

        switchTo().frame($(".i6jjn6"));
        sleep(2000);
        scrollDown();
        clickViaJs_01($(By.xpath("//a[contains(text(),'Confirm order')]")));
        sleep(2000);
        PickingOrderList_Page pickingOrderList_page = new PickingOrderList_Page();
        switchTo().window("Bemakers");
        waitForPageIsLoaded();
        pickingOrderList_page.confirmOrder_Btn.should(Condition.visible,Duration.ofMillis(10000));
        //add check product sku, name, quantity, batch number

        clickViaJs_01(pickingOrderList_page.confirmOrder_Btn);

        pickingOrderList_page.confirmOrderSuccess_Title.shouldHave(Condition.text("The order has been confirmed"));

        /*orders_page.createNewOrder.hover().click();
        CreateNewOrder_Page createNewOrder_page = new CreateNewOrder_Page(CreateNewOrder_Page.URL);
        createNewOrder_page.searchForCustomer_Input.hover().click();
//        createNewOrder_page.searchForCustomer_Input.setValue("denmarkcustomer b"); sleep(2000);
        createNewOrder_page.searchForCustomer_Input.setValue("Danish One Consumer"); sleep(2000);
        $(By.xpath("//div[@role='presentation']")).shouldHave() ;
//        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("denmarkcustomer b"), Duration.ofMillis(10000));
        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("Danish One Consumer"), Duration.ofMillis(10000));
                createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.DOWN);
        createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.ENTER);
        $(By.xpath("//header/h2")).shouldHave(Condition.text("Danish One Consumer"), Duration.ofMillis(4000));
        createNewOrder_page.next_Btn.hover().click();
        createNewOrder_page.searchForProduct_Input.hover().click();

        createNewOrder_page.searchForProduct_Input.setValue("Wine"); sleep(2000);
        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("Wine"), Duration.ofMillis(10000));
        sleep(1000);
        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.DOWN);
        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.ENTER);
        createNewOrder_page.quantity_Input.setValue("1");
        createNewOrder_page.price_Input.shouldHave(Condition.value("10,00"));
        createNewOrder_page.priceInclVAR_Label.shouldHave(Condition.text(createNewOrder_page.priceInclVAR_TextLabel+ "12,50" + " DKK"));
        createNewOrder_page.addToOrder_Btn.hover().click();
        sleep(2000);
        createNewOrder_page.next_Btn.hover().click();
        createNewOrder_page.shippingWeight.shouldHave(Condition.value("1,6"));
        createNewOrder_page.postnordCariarLogo.shouldBe(Condition.visible);
        createNewOrder_page.next_Btn.hover().click();
        sleep(3000);
        createNewOrder_page.submit_Btn.hover().click();
        sleep(2000);
        createNewOrder_page.submitMoodalDialog_Btn.hover().click();
        sleep(2000);
        OrderSummary_Page orderSummary_page = new OrderSummary_Page();
        String orderTitle =  orderSummary_page.orderNameTitle.getText();
        String orderNumber = orderTitle.replaceAll("Order #", "");
        maker.setOrderNumber(orderNumber);
        closeWebDriver();*/









    }
}
