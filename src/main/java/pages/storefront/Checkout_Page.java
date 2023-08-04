package pages.storefront;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class Checkout_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/orders";
    public SelenideElement emailInputField = $("#checkout_email");

    public SelenideElement fullNameInputField = $("#checkout_shipping_name");
    public SelenideElement streetHouseInputField = $("#checkout_shipping_line1");
    public SelenideElement postCodeInputField = $("#checkout_shipping_postcode");
    public SelenideElement cityInputField = $("#checkout_shipping_city");
    public SelenideElement phoneInputField = $("#checkout_shipping_phone_number");

    public SelenideElement postnordCollect = $("span input#checkout_shipping_method_postnord_collect");

    public SelenideElement fullNameBillingInputField = $("#checkout_billing_name");
    public SelenideElement streetHouseBillingInputField = $("#checkout_billing_line1");
    public SelenideElement postCodeBillingInputField = $("#checkout_billing_postcode");
    public SelenideElement cityBillingInputField = $("#checkout_billing_city");
    public SelenideElement phoneBillingInputField = $("#checkout_billing_phone_number");

    public SelenideElement continue_Btn = $(By.xpath("//button[@type='submit']"));
    public SelenideElement paymentSuccessfulTitle = $(By.xpath("//div//div/h1[contains(text(),'Payment successful')]"));
    public SelenideElement thanksForOrderingTitle = $(By.xpath("//div//div/p[contains(text(),'Thanks for ordering')]"));

/*    public Checkout_Page(String url) {
        super(url);
    }*/

}
