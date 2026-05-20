package org.example.businessLayer;

import org.example.dataAccessLayer.BillDAO;
import org.example.model.Bill;

import java.util.List;

public class BillBLL {

    private final BillDAO billDAO = new BillDAO();

    public List<Bill> findAll() {
        return billDAO.findAll();
    }
}