package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class OrderSummary_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/orders/";

    public SelenideElement orderNameTitle = $("h1.pageTitle");
    public SelenideElement ordersLink = $("li.MuiBreadcrumbs-li a");

/*
    public OrderSummary_Page(String url) {
        super(url);
    }*/

}
