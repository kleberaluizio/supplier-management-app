package com.kleberaluizio.api.controller;

import com.kleberaluizio.api.config.CorsConfig;
import com.kleberaluizio.api.model.Supplier;
import com.kleberaluizio.api.model.SupplierDTO;
import com.kleberaluizio.api.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("suppliers")
@Import(CorsConfig.class)
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        return supplierService.addSupplier(supplierDTO);
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getSuppliers(){
        return supplierService.getAllSuppliers();
    }
    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getSupplier(@PathVariable("supplierId") Long supplierId){
        return supplierService.getSupplier(supplierId);
    }

    @PutMapping
    @RequestMapping("/{supplierId}")
    @Transactional
    public ResponseEntity updateSupplier(@PathVariable("supplierId") Long supplierId,
                                                  @Valid @RequestBody SupplierDTO supplierDTO){

        return supplierService.updateSupplier(supplierId, supplierDTO);

    }

    @DeleteMapping("/{supplierId}")
    @Transactional
    public ResponseEntity deleteSupplier(@PathVariable("supplierId") Long supplierId){
        return supplierService.deleteSupplierById(supplierId);
    }

}
