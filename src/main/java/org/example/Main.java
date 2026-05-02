package org.example;

import org.example.connection.ConnectionFactory;
import org.example.dataAccessLayer.ClientDAO;
import org.example.model.Client;

public class Main {
    public static void main(String[] args) {

        System.out.println(ConnectionFactory.getConnection());

        ClientDAO dao = new ClientDAO();

        Client c = new Client(0, "Bogdan", "bogdan@mail.com", "Cluj");
        dao.insert(c);

        System.out.println(dao.findAll());
    }
}