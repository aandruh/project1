package tescases;

import org.testng.annotations.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static org.testng.Assert.assertEquals;


public class ExampleOneTest extends BaseTest {

    @Test
    public void testAndersenWineryShopGlobal() throws Exception {
        open("https://andersen-winery.bemakers.shop");
        $("img[src='https://227602d2647c3e20bd63.ucr.io/-/quality/smart_retina/https://bemakers-public-production.s3.eu-west-1.amazonaws.com/sh3vavc688mjq9o437ws8ozyhrj1']").shouldBe(visible);
        assertEquals(title(), "Andersen Winery");
        $("a[href='/dk']").shouldBe(visible);
        $("a[href='/de']").shouldBe(visible);
        $("a[href='/nl']").shouldBe(visible);
        $("a[href='/se']").shouldBe(visible);
    }

    @Test
    public void testAndersenWineryShopDK() throws Exception {
        open("https://andersen-winery.bemakers.shop/dk");
        $("#age-modal-front").shouldBe(visible);
        $("#confirm-age").hover().click();
        $("a[href='/dk/products/ben-a-test']").hover().click();
        $("h1.h1").shouldHave(text("Ben A"));
        $("h1+p").shouldHave(text("249,00"));
        $(".p-small.text-font").shouldHave(text("\n" +
                "              0,75 L\n" +
                "              (332,00 DKK/L)\n" +
                "              "));
        $(".product-details.mt-12 p.quote").shouldHave(text("Guld og vinder af frugtvin kategorien ved Nordic International Cider Awards (NICA) 2022 i Bergen, Norge"));
        $("button[type='submit']").hover().click();
        $("a[href='/dk/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/dk/cart']").hover().click();
        $("a[href='/dk/checkout']").isDisplayed();

    }

    @Test
    public void testAndersenWineryShopDE() throws Exception {
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
        $("a[href='/de/cart'] #cart_total_count").shouldHave(text("1"), Duration.ofMillis(4000));
        $("a[href='/de/cart']").hover().click();
        $("a[href='/de/checkout']").isDisplayed();
    }

    @Test
    public void testAndersenWineryShopSE() throws Exception {
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
        $(".product-details.mt-12 p.quote").shouldHave(text("Awarded Best Sparkling Fruitwine at the Danish Wine awards 2022"));
        $("button[type='submit']").hover().click();
        $("a[href='/se/cart'] #cart_total_count").shouldHave(text("1"));
        $("a[href='/se/cart']").hover().click();
        $("a[href='/se/checkout']").isDisplayed();
    }

    @Test
    public void testAndersenWineryShopNL() throws Exception {
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
