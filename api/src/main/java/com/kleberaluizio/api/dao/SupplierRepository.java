package com.kleberaluizio.api.dao;

import com.kleberaluizio.api.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
