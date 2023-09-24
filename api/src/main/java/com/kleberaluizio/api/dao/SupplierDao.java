package com.kleberaluizio.api.dao;

import com.kleberaluizio.api.model.Supplier;

import java.util.List;

public interface SupplierDao {

    List<Supplier> selectAllSuppliers();
}
