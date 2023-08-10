package tescases.staging.app;

import Models.TestOrder_Model;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class CreateOrderByMakerDistributor_Test extends BaseTest {
    private TestOrder_Model order = new TestOrder_Model();

    @BeforeMethod
    public void setUp() throws Exception {
        order.setCustomerName("Distributor");


        order.setProductEditPageUrl("https://app.staging-bemakers.com/products/beverages/MTpQcm9kdWN0Ojk1MA/edit");
        order.setProductID("MTpQcm9kdWN0Ojk1MA");
        order.setMakerEmail("alex+dailydk@bemakers.com");
        order.setMakerPassword("1111");
        order.setMakerToken(Query.getAuthToken(order.getMakerEmail(), order.getMakerPassword()));
        order.setCustomerAddress("Distributor Consignee\r\n" + "Osseweg 87\r\n" + "1\r\n" + "2300 Copenhagen\r\n" + "Denmark");


    }


    @Test
    public void testOrderCreation() throws Exception {
        order.setProductQuantityAvailable(Integer.parseInt(Query.getQuantityAvailable(order.getMakerToken(), order.getProductID())));
        open(Login_Page.URL);
        Login_Page login_page = new Login_Page();
        waitForPageIsLoaded();
        login_page.email_Input.setValue(order.getMakerEmail());
        login_page.password_Input.setValue(order.getMakerPassword());
        waitForPageIsLoaded();
        login_page.continue_Btn.hover().click();
        waitForPageIsLoaded();

        Orders_Page orders_page = new Orders_Page(Orders_Page.URL);
//        orders_page = new Orders_Page(Orders_Page.URL);

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
        closeWebDriver();
        String pickingListUrl = ReceiveMailApi.getConfirmOrderLink(order.getOrderNumber());
        open(pickingListUrl);

        PickingOrderList_Page pickingOrderList_page = new PickingOrderList_Page();
        waitForPageIsLoaded();
        pickingOrderList_page.confirmOrder_Btn.should(Condition.visible, Duration.ofMillis(10000));
        clickViaJs_01(pickingOrderList_page.confirmOrder_Btn);
        pickingOrderList_page.confirmOrderSuccess_Title.shouldHave(Condition.text("The order has been confirmed"));
        open(Login_Page.URL);
        waitForPageIsLoaded();
//
////        Login_Page login_page  = new Login_Page();
        login_page.email_Input.setValue(order.getMakerEmail());
        login_page.password_Input.setValue(order.getMakerPassword());
        login_page.continue_Btn.hover().click();
        orders_page = new Orders_Page(Orders_Page.URL);
        orders_page.lastOrderActionButton.hover().click();
        String invoiceLink = orders_page.lastOrderDownloadInvoiceButton.getAttribute("href");
        String pdfExtractedContent = PDFReader.getPDFContent(invoiceLink);
        System.out.println(pdfExtractedContent);
        assertTrue(pdfExtractedContent.contains(order.getCustomerAddress()));
        assertTrue(pdfExtractedContent.contains("WINE Wine (ECO) 1 10,00 10,00"));
        assertTrue(pdfExtractedContent.contains("Shipping 1 0,00 0,00"));
        assertTrue(pdfExtractedContent.contains("This order contains ecological products"));
        assertTrue(pdfExtractedContent.contains("DK-ØKO-100"));
        assertTrue(pdfExtractedContent.contains("Subtotal: 10,00"));
        assertTrue(pdfExtractedContent.contains("25% VAT: 2,50"));
        assertTrue(pdfExtractedContent.contains("Total (DKK): 12,50"));
        assertTrue(pdfExtractedContent.contains("Delivery to:\r\n" + "Distributor Company\r\n" + order.getCustomerAddress()));

        assertEquals(orders_page.lastOrderNumber.getText(), order.getOrderNumber());
        assertEquals(Integer.parseInt(Query.getQuantityAvailable(order.getMakerToken(), order.getProductID())), order.getProductQuantityAvailable() - 1);


    }
}
