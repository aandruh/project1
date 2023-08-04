package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class NewOrders_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/orders/tabs/NEW";
    public SelenideElement createOrder = $(By.xpath("//button[contains(text(),'Create order')]"));
    public SelenideElement lastOrderCheckbox = $(By.xpath("//tbody/tr[1]/td[1]/span[2]/span"));
    public SelenideElement lastOrderNumber = $(By.xpath("//tbody/tr[1]/td[2]/span[2]"));
    public SelenideElement confirmButton = $(By.xpath("//table//tr//button[contains(text(),'Confirm')]"));

//    public New_Orders_Page(String url) {
//        super(url);
//    }

}
