package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewOrder_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/orders/new";

    public SelenideElement searchForCustomer_Input = $(By.xpath("//div[@name='customer_search']//input"));
    public SelenideElement searchForProduct_Input = $(By.xpath("//div[@name='product_search']//input"));
    public SelenideElement next_Btn = $(By.xpath("//button[contains(text(),'Next')]"));
    public SelenideElement quantity_Input = $(By.xpath("//input[@name='quantity']"));
    public SelenideElement price_Input = $(By.xpath("//input[@name='price.amount']"));
    public SelenideElement priceInclVAT_Label = $(By.xpath("//p[contains(text(),'Price (Incl. VAT): ')]"));
    public String priceInclVAR_TextLabel = "Price (Incl. VAT): ";
    public SelenideElement addToOrder_Btn = $(By.xpath("//button[@type='submit']"));
    public SelenideElement shippingWeight = $(By.xpath("//input[@name='shippingWeight']"));
    public SelenideElement postnordCarrierLogo = $("div svg#postnord-logo");
    public SelenideElement parcelPackage = $(By.xpath("//*[@id=\"shippingPackage\"]/div/div[1]/div"));
    public SelenideElement submit_Btn = $(By.xpath("//button[contains(text(),'Submit')]"));
    public SelenideElement orderNameTitle = $("h1.pageTitle");
    public SelenideElement submitMoodalDialog_Btn = $(By.xpath("//div[@role='presentation'] //button[contains(text(),'Submit')]"));



    public CreateNewOrder_Page(String url) {
        super(url);
    }

}
