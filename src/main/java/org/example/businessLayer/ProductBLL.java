package org.example.businessLayer;

import org.example.dataAccessLayer.ProductDAO;
import org.example.model.Product;

import java.util.List;

/**
 * Clasa responsabila pentru logica de business asociata produselor.
 * Realizeaza validarea datelor si operatiile CRUD pentru produse.
 */

public class ProductBLL {

    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Returneaza toate produsele existente.
     *
     * @return lista de produse
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    /**
     * Cauta un produs dupa id.
     *
     * @param id id-ul produsului
     * @return produsul gasit
     * @throws IllegalArgumentException daca produsul nu exista
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);

        if (product == null) {
            throw new IllegalArgumentException("Produsul nu exista.");
        }

        return product;
    }

    /**
     * Insereaza un produs nou dupa validarea datelor.
     *
     * @param product produsul care va fi inserat
     */
    public void insert(Product product) {
        validateProduct(product);
        productDAO.insert(product);
    }

    /**
     * Actualizeaza datele unui produs existent.
     *
     * @param product produsul care va fi actualizat
     */
    public void update(Product product) {
        validateProduct(product);
        productDAO.update(product);
    }

    /**
     * Sterge un produs dupa id.
     *
     * @param id id-ul produsului
     */
    public void delete(int id) {
        findById(id);
        productDAO.delete(id);
    }

    /**
     * Valideaza datele produsului.
     *
     * @param product produsul verificat
     * @throws IllegalArgumentException daca datele sunt invalide
     */
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