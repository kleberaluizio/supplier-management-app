package com.kleberaluizio.api.service;

import com.kleberaluizio.api.dao.SupplierDao;
import com.kleberaluizio.api.model.Supplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierDao supplierDao;

    public SupplierService(@Qualifier("jpa") SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDao.selectAllSuppliers();
    }
}
