package com.kleberaluizio.api.controller;

import com.kleberaluizio.api.model.Supplier;
import com.kleberaluizio.api.service.SupplierService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getSuppliers(){

        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.status(Response.SC_ACCEPTED).body(suppliers);
    }
}
