package cartEntities;

public class product {
    private String productName;
    private String taxID;
    private int productQuantity;
    private double productPrice, productTax, productFinalSale;


    public product() {
    }

    public product(String productName, String taxID, int productQuantity, double productPrice, double productTax, double productFinalSale) {
        this.productName = productName;
        this.taxID = taxID;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productTax = productPrice;
        this.productFinalSale = productFinalSale;
    }

    public double getProductFinalSale() {
        return productFinalSale;
    }

    public void setProductFinalSale(double productFinalSale) {
        this.productFinalSale = productFinalSale;
    }

    public double getProductTax() {
        return productTax;
    }

    public void setProductTax(double productTax) {
        this.productTax = productTax;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
