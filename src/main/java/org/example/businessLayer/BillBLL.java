package org.example.businessLayer;

import org.example.dataAccessLayer.BillDAO;
import org.example.model.Bill;

import java.util.List;

/**
 * Clasa responsabila pentru logica de business asociata facturilor.
 */

public class BillBLL {

    private final BillDAO billDAO = new BillDAO();

    /**
     * Returneaza toate facturile existente.
     *
     * @return lista de facturi
     */
    public List<Bill> findAll() {
        return billDAO.findAll();
    }
}