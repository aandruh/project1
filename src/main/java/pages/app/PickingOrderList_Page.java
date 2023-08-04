package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class PickingOrderList_Page extends PageBase {

    public SelenideElement confirmOrder_Btn = $(By.xpath("//button[contains(text(),'Confirm order')]/span"));
    public SelenideElement confirmOrderSuccess_Title = $(By.xpath("//h2"));


}



