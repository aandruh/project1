package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmOrder_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/orders";
    public SelenideElement confirmOrderButton = $(By.xpath("//button[contains(text(),'Confirm order')]"));
    public SelenideElement closeButton = $(By.xpath("//button[contains(text(),'Close')]"));
    public SelenideElement confirmOrderSuccess_Title = $(By.xpath("//h2"));
//    public SelenideElement lastOrderCheckboxOnNewTab = $(By.xpath("//tbody/tr[1]/td[1]/span[2]/span"));
//    public SelenideElement lastOrderNumberOnNewTab = $(By.xpath("//tbody/tr[1]/td[2]/span[2]"));
//    public SelenideElement lastOrderActionButton = $(By.xpath("//tbody/tr[1]/td[8]"));
//    public SelenideElement lastOrderDownloadInvoiceButton = $(By.xpath("//tbody/tr[1]/td[8]//div[@role='presentation']//li//a"));
//    public SelenideElement newTab = $(By.xpath("//a/div/div[contains(text(),'NEW')]"));
//    public SelenideElement confirmButton = $(By.xpath("//table//tr//button[contains(text(),'Confirm')]"));

//    public ConfirmOrder_Page(String url) {
//        super(url);
//    }

}
