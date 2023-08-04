package Models;

public class StockMovement_Model {


    private String marketCountry;
    private String orderNumber;
    private String customerName;



    private String customerAddress;
    private String productId;
    private String makerEmail;
    private String makerPassword;
    private String makerToken;
    private String productEditPageUrl;
    private String productID;
    private int productQuantityAvailable;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getProductEditPageUrl() {
        return productEditPageUrl;
    }

    public void setProductEditPageUrl(String productEditPageUrl) {
        this.productEditPageUrl = productEditPageUrl;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getProductQuantityAvailable() {
        return productQuantityAvailable;
    }

    public void setProductQuantityAvailable(int productQuantityAvailable) {
        this.productQuantityAvailable = productQuantityAvailable;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMakerEmail() {
        return makerEmail;
    }

    public void setMakerEmail(String makerEmail) {
        this.makerEmail = makerEmail;
    }

    public String getMakerPassword() {
        return makerPassword;
    }

    public void setMakerPassword(String makerPassword) {
        this.makerPassword = makerPassword;
    }

    public String getMakerToken() {
        return makerToken;
    }

    public void setMakerToken(String makerToken) {
        this.makerToken = makerToken;
    }

    public void setMarketCountry(String marketCountry) {
        this.marketCountry = marketCountry;
    }

    public String getMarketCountry() {
        return marketCountry;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerDeliveryAddress) {
        this.customerAddress = customerDeliveryAddress;
    }
}
