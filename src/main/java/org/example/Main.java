package org.example;

import org.example.connection.ConnectionFactory;
import org.example.dataAccessLayer.ClientDAO;
import org.example.model.Client;

public class Main {
    public static void main(String[] args) {

        System.out.println("Orders Management app started!");
        ClientDAO dao = new Client(1, "Bogdan Updated", "updated@mail.com", "Cluj");

        System.out.println(dao.findAll());

        dao.delete(2);
        System.out.println(dao.findAll());
    }
}
//