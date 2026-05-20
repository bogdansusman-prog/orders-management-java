package org.example.businessLayer;

import org.example.dataAccessLayer.ProductDAO;
import org.example.model.Product;

import java.util.List;

public class ProductBLL {

    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(int id) {
        Product product = productDAO.findById(id);

        if (product == null) {
            throw new IllegalArgumentException("Produsul nu exista.");
        }

        return product;
    }

    public void insert(Product product) {
        validateProduct(product);
        productDAO.insert(product);
    }

    public void update(Product product) {
        validateProduct(product);
        productDAO.update(product);
    }

    public void delete(int id) {
        findById(id);
        productDAO.delete(id);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Numele produsului nu poate fi gol.");
        }

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Pretul produsului nu poate fi negativ.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stocul produsului nu poate fi negativ.");
        }
    }
}