package pages.storefront;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;


public class Product_Page extends PageBase {
    public SelenideElement addToCart_Btn = $("button[type='submit']");
    public SelenideElement cartCounter = $("a[href='/dk/cart'] #cart_total_count");
    public SelenideElement confirmAge_Btn = $("#confirm-age");
    public SelenideElement streetHouseInputField = $("#checkout_shipping_line1");
    public SelenideElement postCodeInputField = $("#checkout_shipping_postcode");
    public SelenideElement cityInputField = $("#checkout_shipping_city");
    public SelenideElement phoneInputField = $("#checkout_shipping_phone_number");
    public SelenideElement continue_Btn = $(By.xpath("//button[@type='submit']"));
    public SelenideElement paymentSuccessfulTitle = $(By.xpath("//div//div/h1[contains(text(),'Payment successful')]"));
    public SelenideElement thanksForOrderingTitle = $(By.xpath("//div//div/p[contains(text(),'Thanks for ordering')]"));



}

