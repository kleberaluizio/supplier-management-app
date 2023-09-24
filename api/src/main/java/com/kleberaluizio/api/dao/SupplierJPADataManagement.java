package com.kleberaluizio.api.dao;

import com.kleberaluizio.api.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public class SupplierJPADataManagement implements SupplierDao{

    private final SupplierRepository supplierRepository;

    public SupplierJPADataManagement(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> selectAllSuppliers() {
        return supplierRepository.findAll();
    }
}
