package org.example.dataAccessLayer;

import org.example.connection.ConnectionFactory;
import org.example.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa responsabila pentru operatiile de acces la baza de date
 * asociate facturilor generate dupa plasarea comenzilor.
 */

public class BillDAO {
    /**
     * Insereaza o factura in baza de date.
     *
     * @param bill factura care va fi inserata
     */
    public void insert(Bill bill){
        String query = """
        INSERT INTO bill
        ("orderId", "clientName", "productName", quantity, "totalPrice")
        VALUES (?, ?, ?, ?, ?)
        """;

        try(Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.quantity());
            statement.setDouble(5, bill.totalPrice());

            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returneaza toate facturile existente in baza de date.
     *
     * @return lista de facturi
     */
    public List<Bill> findAll(){
        List<Bill> bills = new ArrayList<>();

        String query = "SELECT * FROM bill order by id desc";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery()){

            while(rs.next()){
                Bill bill = new Bill(
                        rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getString("clientName"),
                        rs.getString("productName"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice")
                );
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;

    }
}
