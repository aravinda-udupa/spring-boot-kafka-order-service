package common;

public class Order {
    Integer id;

    String name;
    Integer productId;
    int qty;
    int unitPrice;
    String status;

    public Order() {

    }
    public Order(Integer id, String name, Integer productId, int qty, int unitPrice, String status) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
