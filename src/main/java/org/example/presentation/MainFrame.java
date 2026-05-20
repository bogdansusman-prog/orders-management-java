package org.example.presentation;

import org.example.businessLayer.ClientBLL;
import org.example.businessLayer.ProductBLL;
import org.example.businessLayer.OrderBLL;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable clientTable;
    private JTextField clientNameField;
    private JTextField clientEmailField;
    private JTextField clientAddressField;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewClientsButton;
    private JTextField productStockField;
    private JTextField productPriceField;
    private JTextField productNameField;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton viewProductButton;
    private JTable productTable;
    private JComboBox<String> clientComboBox;
    private JComboBox<String> productComboBox;
    private JButton placeOrderButton;
    private JTable orderTable;
    private JTextField orderQuantityField;

    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();
    private final OrderBLL orderBLL = new OrderBLL();

    public MainFrame(){
        setTitle("Orders Management");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);

        initListeners();
        loadClients();
        loadProducts();
        loadOrders();
        loadCombos();
        clearProductFields();
        initTableSelections();
    }

    private void initListeners() {
        viewClientsButton.addActionListener(e -> loadClients());
        viewProductButton.addActionListener(e -> loadProducts());
        placeOrderButton.addActionListener(e -> placeOrder());

        addClientButton.addActionListener(e -> {
            try {
                Client client = new Client(
                        0,
                        clientNameField.getText(),
                        clientEmailField.getText(),
                        clientAddressField.getText()
                );

                clientBLL.insert(client);
                loadClients();
                loadCombos();
                clearClientFields();

                JOptionPane.showMessageDialog(this, "Client added.");
            } catch (Exception ex) {
                showError(ex);
            }
        });

        addProductButton.addActionListener(e -> {
            try {
                Product product = new Product(
                        0,
                        productNameField.getText(),
                        Double.parseDouble(productPriceField.getText()),
                        Integer.parseInt(productStockField.getText())
                );

                productBLL.insert(product);
                loadProducts();
                loadCombos();
                clearProductFields();

                JOptionPane.showMessageDialog(this, "Product added.");
            } catch (Exception ex) {
                showError(ex);
            }
        });
        deleteClientButton.addActionListener(e -> {
            try {
                int selectedRow = clientTable.getSelectedRow();

                if(selectedRow == -1){
                    throw new IllegalArgumentException("Selecteaza un client.");
                }

                int id = (int) clientTable.getValueAt(selectedRow, 0);

                clientBLL.delete(id);

                loadClients();
                loadCombos();
                clearClientFields();

                JOptionPane.showMessageDialog(this, "Client deleted.");
            } catch (Exception ex){
                showError(ex);
            }
        });
        deleteProductButton.addActionListener(e -> {
            try {
                int selectedRow = productTable.getSelectedRow();

                if(selectedRow == -1){
                    throw new IllegalArgumentException("Selecteaza un produs.");
                }

                int id = (int) productTable.getValueAt(selectedRow, 0);

                productBLL.delete(id);

                loadProducts();
                loadCombos();
                clearProductFields();

                JOptionPane.showMessageDialog(this, "Product deleted.");
            } catch (Exception ex){
                showError(ex);
            }
        });
        editClientButton.addActionListener(e -> {
            try {
                int selectedRow = clientTable.getSelectedRow();

                if(selectedRow == -1){
                    throw new IllegalArgumentException("Selecteaza un client.");
                }

                int id = (int) clientTable.getValueAt(selectedRow, 0);

                Client client = new Client(
                        id,
                        clientNameField.getText(),
                        clientEmailField.getText(),
                        clientAddressField.getText()
                );

                clientBLL.update(client);

                loadClients();
                loadCombos();
                clearClientFields();

                JOptionPane.showMessageDialog(this, "Client updated.");
            } catch (Exception ex){
                showError(ex);
            }
        });
        editProductButton.addActionListener(e -> {
            try {
                int selectedRow = productTable.getSelectedRow();

                if(selectedRow == -1){
                    throw new IllegalArgumentException("Selecteaza un produs.");
                }

                int id = (int) productTable.getValueAt(selectedRow, 0);

                Product product = new Product(
                        id,
                        productNameField.getText(),
                        Double.parseDouble(productPriceField.getText()),
                        Integer.parseInt(productStockField.getText())
                );

                productBLL.update(product);

                loadProducts();
                loadCombos();
                clearProductFields();

                JOptionPane.showMessageDialog(this, "Product updated.");
            } catch (Exception ex){
                showError(ex);
            }
        });
    }

    private void loadClients() {
        clientTable.setModel(
                ReflectionTableModel.createTableModel(clientBLL.findAll())
        );
    }

    private void loadProducts() {
        productTable.setModel(
                ReflectionTableModel.createTableModel(productBLL.findAll())
        );
    }

    private void loadOrders() {
        orderTable.setModel(
                ReflectionTableModel.createTableModel(orderBLL.findAll())
        );
    }

    private void loadCombos() {
        clientComboBox.removeAllItems();
        productComboBox.removeAllItems();

        for (Client c : clientBLL.findAll()) {
            clientComboBox.addItem(c.getId() + " - " + c.getName());
        }

        for (Product p : productBLL.findAll()) {
            productComboBox.addItem(p.getId() + " - " + p.getName());
        }
    }

    private void placeOrder() {
        try {
            String clientText = (String) clientComboBox.getSelectedItem();
            String productText = (String) productComboBox.getSelectedItem();

            if (clientText == null || productText == null) {
                throw new IllegalArgumentException("Selecteaza client si produs.");
            }

            int clientId = Integer.parseInt(clientText.split(" - ")[0]);
            int productId = Integer.parseInt(productText.split(" - ")[0]);
            int quantity = Integer.parseInt(orderQuantityField.getText());

            orderBLL.placeOrder(clientId, productId, quantity);

            loadOrders();
            loadProducts();
            loadCombos();

            orderQuantityField.setText("");

            JOptionPane.showMessageDialog(this, "Order placed.");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void clearClientFields() {
        clientNameField.setText("");
        clientEmailField.setText("");
        clientAddressField.setText("");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    private void clearProductFields() {
        productNameField.setText("");
        productPriceField.setText("");
        productStockField.setText("");
    }
    private void initTableSelections() {

        clientTable.getSelectionModel().addListSelectionListener(e -> {

            int row = clientTable.getSelectedRow();

            if(row != -1){
                clientNameField.setText(
                        clientTable.getValueAt(row, 1).toString()
                );

                clientEmailField.setText(
                        clientTable.getValueAt(row, 2).toString()
                );

                clientAddressField.setText(
                        clientTable.getValueAt(row, 3).toString()
                );
            }
        });

        productTable.getSelectionModel().addListSelectionListener(e -> {

            int row = productTable.getSelectedRow();

            if(row != -1){
                productNameField.setText(
                        productTable.getValueAt(row, 1).toString()
                );

                productPriceField.setText(
                        productTable.getValueAt(row, 2).toString()
                );

                productStockField.setText(
                        productTable.getValueAt(row, 3).toString()
                );
            }
        });
    }
}
