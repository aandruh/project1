package tescases.staging.app;

import Models.TestOrder_Model;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.app.*;
import tescases.BaseTest;
import utils.PDFReader;
import utils.Query;
import utils.ReceiveMailApi;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.PageBase.clickViaJs_01;
import static pages.PageBase.waitForPageIsLoaded;

public class CreateOrderByMakerBusiness_ConfirmViaApp_Test extends BaseTest {
    private TestOrder_Model order = new TestOrder_Model();

    @BeforeMethod
    public void setUp() throws Exception {
        order.setCustomerName("Danish Business");


        order.setProductEditPageUrl("https://app.staging-bemakers.com/products/beverages/MTpQcm9kdWN0Ojk1MA/edit");
        order.setProductID("MTpQcm9kdWN0Ojk1MA");
        order.setMakerEmail("alex+dailydk@bemakers.com");
        order.setMakerPassword("Test1111");
        order.setMakerToken(Query.getAuthToken(order.getMakerEmail(), order.getMakerPassword()));
        order.setCustomerAddress("Danish Business Adr\r\n" + "Kløvermarksvej 70D,\r\n" + "12\r\n" + "2300 Copenhagen\r\n" + "Denmark");



    }


    @Test
    public void testOrderCreation() throws Exception {
        order.setProductQuantityAvailable(Integer.parseInt(Query.getQuantityAvailable(order.getMakerToken(), order.getProductID())));
        try{
        open(Login_Page.URL);}
        catch (TimeoutException e){
            refresh();
            System.out.println(e);
        }
        makerLogin(order);


        Orders_Page orders_page = new Orders_Page(Orders_Page.URL);


        orders_page.createOrder.hover().click();
        CreateNewOrder_Page createNewOrder_page = new CreateNewOrder_Page(CreateNewOrder_Page.URL);
        createNewOrder_page.searchForCustomer_Input.hover().click();
        createNewOrder_page.searchForCustomer_Input.setValue(order.getCustomerName());
        sleep(10000);
        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text(order.getCustomerName()), Duration.ofMillis(10000));
        createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.DOWN);
        createNewOrder_page.searchForCustomer_Input.sendKeys(Keys.ENTER);
        $(By.xpath("//section[1]/div/div/div/div[2]/span[1]")).shouldHave(Condition.text(order.getCustomerName()), Duration.ofMillis(4000));
        createNewOrder_page.next_Btn.hover().click();
        createNewOrder_page.searchForProduct_Input.hover().click();
        createNewOrder_page.searchForProduct_Input.setValue("Wine");
        sleep(2000);
        $(By.xpath("//div[@role='presentation']")).shouldHave(Condition.text("Wine"), Duration.ofMillis(10000));
        sleep(1000);
        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.DOWN);
        createNewOrder_page.searchForProduct_Input.sendKeys(Keys.ENTER);
        createNewOrder_page.quantity_Input.setValue("1");
        createNewOrder_page.price_Input.shouldHave(Condition.value("10,00"));
        createNewOrder_page.priceInclVAT_Label.shouldHave(Condition.text(createNewOrder_page.priceInclVAR_TextLabel + "12,50" + " DKK"));
        createNewOrder_page.addToOrder_Btn.hover().click();
        sleep(2000);
        createNewOrder_page.next_Btn.hover().click();
        createNewOrder_page.shippingWeight.shouldHave(Condition.value("1,6"));
        createNewOrder_page.postnordCarrierLogo.shouldBe(Condition.visible);
//        createNewOrder_page.parcelPackage.hover().click();
        createNewOrder_page.next_Btn.hover().click();
        sleep(3000);
        createNewOrder_page.submit_Btn.hover().click();
        sleep(2000);
        createNewOrder_page.submitMoodalDialog_Btn.hover().click();
        sleep(2000);
        OrderSummary_Page orderSummary_page = new OrderSummary_Page();
        String orderTitle = orderSummary_page.orderNameTitle.getText();
        order.setOrderNumber(orderTitle.replaceAll("Order #", ""));
        sleep(10000);
        orderSummary_page.ordersLink.hover().click();
        orders_page.newTab.hover().click();
        NewOrders_Page new_orders_page = new NewOrders_Page();

        new_orders_page.lastOrderNumber.shouldHave(Condition.text(order.getOrderNumber()));
        new_orders_page.lastOrderCheckbox.hover().click();
        new_orders_page.confirmButton.hover().click();

        ConfirmOrder_Page confirmOrder_page = new ConfirmOrder_Page();
        confirmOrder_page.confirmOrderButton.hover().click();
        confirmOrder_page.confirmOrderSuccess_Title.shouldHave(Condition.text("Great - we’re processing the paperwork! This will take a few minutes.."));
        confirmOrder_page.closeButton.hover().click();
        sleep(10000);
        orders_page.allTab.hover().click();

        orders_page.lastOrderActionButton.hover().click();
        String invoiceLink = orders_page.lastOrderDownloadInvoiceButton.getAttribute("href");
        String pdfExtractedContent = PDFReader.getPDFContent(invoiceLink);
        System.out.println(pdfExtractedContent);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(pdfExtractedContent.contains(order.getCustomerAddress()));
        assertTrue(pdfExtractedContent.contains("VAT-nr: DK27394698"));
        assertTrue(pdfExtractedContent.contains("WINE Wine (ECO) 1 10,00 10,00"));
        assertTrue(pdfExtractedContent.contains("Shipping 1 0,00 0,00"));
        assertTrue(pdfExtractedContent.contains("This order contains ecological products"));
        assertTrue(pdfExtractedContent.contains("DK-ØKO-100"));
        assertTrue(pdfExtractedContent.contains("Subtotal: 10,00"));
        softAssert.assertTrue(pdfExtractedContent.contains("25% VAT: 2,50"));
        assertTrue(pdfExtractedContent.contains("Total (DKK): 12,50"));
        assertTrue(pdfExtractedContent.contains("Delivery to:\r\n" + "My BC company\r\n" +  order.getCustomerAddress()));

        assertEquals(orders_page.lastOrderNumber.getText(), order.getOrderNumber());
        assertEquals(Integer.parseInt(Query.getQuantityAvailable(order.getMakerToken(), order.getProductID())), order.getProductQuantityAvailable() - 1);


    }
}
