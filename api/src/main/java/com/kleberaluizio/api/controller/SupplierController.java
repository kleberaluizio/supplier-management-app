package com.kleberaluizio.api.controller;

import com.kleberaluizio.api.model.Supplier;
import com.kleberaluizio.api.model.SupplierDTO;
import com.kleberaluizio.api.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        supplierService.addSupplier(supplierDTO);
        return supplierService.addSupplier(supplierDTO);
    }

    @DeleteMapping("/{supplierId}")
    @Transactional
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") Long supplierId){
        return supplierService.deleteSupplierById(supplierId);
    }

//    @PutMapping
//    @Transactional
//    @RequestMapping("/{id}")
//    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDTO supplierDTO){
//
//        return supplierService.updateCustomer(id, supplierDTO);
//
//    }
}
