package org.example.businessLayer;

import org.example.dataAccessLayer.BillDAO;
import org.example.dataAccessLayer.OrderDAO;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Product;

import java.util.List;

public class OrderBLL {

    private final OrderDAO orderDAO = new OrderDAO();
    private final BillDAO billDAO = new BillDAO();

    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();

    public List<Orders> findAll() {
        return orderDAO.findAll();
    }

    public void placeOrder(int clientId, int productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Cantitatea trebuie sa fie pozitiva."
            );
        }

        Client client = clientBLL.findById(clientId);
        Product product = productBLL.findById(productId);

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException(
                    "Stoc insuficient pentru produsul: "
                            + product.getName()
            );
        }

        Orders order = new Orders(
                0,
                clientId,
                productId,
                quantity
        );

        orderDAO.insert(order);

        product.setStock(
                product.getStock() - quantity
        );

        productBLL.update(product);

        Bill bill = new Bill(
                0,
                0,
                client.getName(),
                product.getName(),
                quantity,
                quantity * product.getPrice()
        );

        billDAO.insert(bill);
    }

    public void delete(int id) {
        findById(id);
        orderDAO.delete(id);
    }

    public Orders findById(int id){
        Orders order = orderDAO.findById(id);

        if(order == null){
            throw new IllegalArgumentException("Comanda nu exista.");
        }
        return order;
    }
}