package org.example.model;

public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    public Order(){}

    public Order(int quantity, int productId, int clientId, int id) {
        this.quantity = quantity;
        this.productId = productId;
        this.clientId = clientId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
