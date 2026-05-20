package org.example.model;

/**
 * Clasa model care reprezinta un produs din baza de date.
 * Contine informatii despre numele, pretul si stocul produsului.
 */

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(){}

    /**
     * Creeaza un obiect de tip Product cu informatiile specificate.
     *
     * @param id id-ul produsului
     * @param name numele produsului
     * @param price pretul produsului
     * @param stock stocul produsului
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
