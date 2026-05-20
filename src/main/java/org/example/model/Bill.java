package org.example.model;

/**
 * Reprezinta factura generata pentru o comanda plasata.
 * Contine informatii despre client, produs, cantitate si pretul total.
 */

public record Bill (
    int id,
    int orderId,
    String clientName,
    String productName,
    int quantity,
    double totalPrice
){}