package pages.stripe;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class Stripe_Page extends PageBase {
    public static final String URL = "https://checkout.stripe.com/";
/*    public SelenideElement emailInputField = $("#checkout_email");
    public SelenideElement fullNameInputField = $("#checkout_shipping_name");
    public SelenideElement streetHouseInputField = $("#checkout_shipping_line1");
    public SelenideElement postCodeInputField = $("#checkout_shipping_postcode");
    public SelenideElement cityInputField = $("#checkout_shipping_city");
    public SelenideElement phoneInputField = $("#checkout_shipping_phone_number");
    public SelenideElement continue_Btn = $(By.xpath("//button[@type='submit']"))*/;
    public SelenideElement cardNumberInputField = $("input#cardNumber");
    public SelenideElement cardExpiryInputField = $("input#cardExpiry");
    public SelenideElement cardCvcInputField = $("input#cardCvc");
    public SelenideElement billingNameInputField = $("input#billingName");
    public SelenideElement pay_Btn = $(By.xpath("//button[@type='submit']"));


/*    public Checkout_Page(String url) {
        super(url);
    }*/

}
