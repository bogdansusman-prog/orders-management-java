package org.example.model;

public record Bill (
    int id,
    int orderId,
    String clientName,
    String productName,
    int quantity,
    double totalPrice
){}

//java genereaza automat constructor
//getters
//toString
//equals
//hashCode
