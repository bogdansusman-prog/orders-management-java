package org.example;


import org.example.connection.ConnectionFactory;

public class Main{
public static void main(String[] args){
        System.out.println(ConnectionFactory.getConnection());
    }
}