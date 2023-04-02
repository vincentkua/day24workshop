package nus.iss.day24workshop.model;

public class Orderdetails {

    private String product;
    private Float unitPrice;
    private Integer quantity;
    public Orderdetails() {
    }
    public Orderdetails(String product, Float unitPrice, Integer quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Orderdetails [product=" + product + ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
    }
        
}
