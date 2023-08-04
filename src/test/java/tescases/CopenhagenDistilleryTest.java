package tescases;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;


public class CopenhagenDistilleryTest extends BaseTest {

    @Test
    public void testCopenhagenDistilleryShopGlobal() throws Exception {
        open("https://copenhagen-distillery.bemakers.shop/");
        $("img[src='https://227602d2647c3e20bd63.ucr.io/-/quality/smart_retina/https://bemakers-public-production.s3.eu-west-1.amazonaws.com/9yr6loigxdz8ohg5c070zop2d2j1']").shouldBe(visible);
//        assertEquals(title(), "Copenhagen Distillery");
        $("a[href='/dk']").shouldBe(visible);
        $("a[href='/de']").shouldBe(visible);
        $("a[href='/nl']").shouldBe(visible);
        $("a[href='/se']").shouldBe(visible);
    }

    @Test
    public void testCopenhagenDistilleryShopDK() throws Exception {
        open("https://copenhagen-distillery.bemakers.shop/dk");
        $("#age-modal-front").shouldBe(visible);
        $("#confirm-age").hover().click();
        $("a[href='/dk/products/angelica-gin']").hover().click();
        $("h1.h1").shouldHave(text("ANGELICA GIN"));
        $("h1+p").shouldHave(text("350,00"));
        $(".p-small.text-font").shouldHave(text("0,5 L (700,00 DKK/L)\n" +
                "Incl. 25% VAT"));
        $(".product-details.mt-12 p.quote").shouldHave(text("A CROSS-CULTURAL SPIRIT"));
        $("button[type='submit']").hover().click();
        $("a[href='/dk/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/dk/cart']").hover().click();
        $("a[href='/dk/checkout']").isDisplayed();

    }

    @Test
    public void testandersenWineryShopDE() throws Exception {
        open("https://andersen-winery.bemakers.shop/de");
        $("#age-modal-front").shouldBe(visible);
        $("#confirm-age").hover().click();
        $("a[href='/de/products/ben-a-2021']").hover().click();
        $("h1.h1").shouldHave(text("Ben A"));
        $("h1+p").shouldHave(text("31,99"));
        $(".p-small.text-font").shouldHave(text("\n" +
                "              0,75 L\n" +
                "              (42,65 EUR/L)\n" +
                "              \n" +
                "              Incl. 19% VAT\n" +
                "            "));
        $(".product-details.mt-12 p.quote").shouldHave(text("Awarded Best Sparkling Fruitwine at the Danish Wine awards 2022"));
        $("button[type='submit']").hover().click();
        $("a[href='/de/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/de/cart']").hover().click();
        $("a[href='/de/checkout']").isDisplayed();
    }

    @Test
    public void testandersenWineryShopSE() throws Exception {
        open("https://andersen-winery.bemakers.shop/se");
        $("#age-modal-front").shouldBe(visible);
        $("#confirm-age").hover().click();
        $("a[href='/se/products/ben-a-2021']").hover().click();
        $("h1.h1").shouldHave(text("Ben A"));
        $("h1+p").shouldHave(text("349,00"));
        $(".p-small.text-font").shouldHave(text("\n" +
                "              0,75 L\n" +
                "              (465,33 SEK/L)\n" +
                "              \n" +
                "              Incl. 25% VAT\n" +
                "            "));
        $(".product-details.mt-12 p.quote").shouldHave(text("Belönad med guldmedalj och vinnare av kategorin fruktvin vid Nordic International Cider Awards (NICA) 2022 i Bergen, Norge"));
        $("button[type='submit']").hover().click();
        $("a[href='/se/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/se/cart']").hover().click();
        $("a[href='/se/checkout']").isDisplayed();
    }

    @Test
    public void testandersenWineryShopNL() throws Exception {
        open("https://andersen-winery.bemakers.shop/nl");
        $("#age-modal-front").shouldBe(visible);
        $("#confirm-age").hover().click();
        $("a[href='/nl/products/ben-a-2021']").hover().click();
        $("h1.h1").shouldHave(text("Ben A"));
        $("h1+p").shouldHave(text("31,99"));
        $(".p-small.text-font").shouldHave(text("\n" +
                "              0,75 L\n" +
                "              (42,65 EUR/L)\n" +
                "              \n" +
                "              Incl. 21% VAT\n" +
                "            "));
        $(".product-details.mt-12 p.quote").shouldHave(text("Awarded Best Sparkling Fruitwine at the Danish Wine awards 2022"));
        $("button[type='submit']").hover().click();
        $("a[href='/nl/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/nl/cart']").hover().click();
        $("a[href='/nl/checkout']").isDisplayed();
    }
}
