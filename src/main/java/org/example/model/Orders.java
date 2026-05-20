package org.example.model;

/**
 * Clasa model care reprezinta o comanda plasata de un client.
 * Contine informatii despre client, produs si cantitatea comandata.
 */

public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    public Orders() {
    }

    /**
     * Creeaza un obiect de tip Orders cu informatiile specificate.
     *
     * @param id id-ul comenzii
     * @param clientId id-ul clientului
     * @param productId id-ul produsului
     * @param quantity cantitatea comandata
     */
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Returneaza id-ul comenzii.
     *
     * @return id-ul comenzii
     */
    public int getId() {
        return id;
    }

    /**
     * Seteaza id-ul comenzii.
     *
     * @param id id-ul comenzii
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returneaza id-ul clientului.
     *
     * @return id-ul clientului
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Seteaza id-ul clientului.
     *
     * @param clientId id-ul clientului
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Returneaza id-ul produsului.
     *
     * @return id-ul produsului
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Seteaza id-ul clientului.
     *
     * @param productId id-ul
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Returneaza cantitatea produsului.
     *
     * @return cantitatea produsului
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Seteaza cantitatea produsului.
     *
     * @param quantity cantitatea produsului
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}