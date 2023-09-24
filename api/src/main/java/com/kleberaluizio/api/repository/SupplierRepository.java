package com.kleberaluizio.api.repository;

import com.kleberaluizio.api.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsSupplierByCnpj(String cnpj);
}
