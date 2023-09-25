package com.kleberaluizio.api.service;

import com.kleberaluizio.api.model.Supplier;
import com.kleberaluizio.api.model.SupplierDTO;
import com.kleberaluizio.api.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity addSupplier(SupplierDTO registrationSupplierDTO){

        // Check if cnpj already exists
        String cnpj = registrationSupplierDTO.cnpj();

        if (supplierRepository.existsSupplierByCnpj(cnpj)) {
            String message = "Fornecedor com CNPJ duplicado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Supplier newSupplier = new Supplier(registrationSupplierDTO);

        supplierRepository.save(newSupplier);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    public ResponseEntity<?> getSupplier(Long supplierId){
        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(supplierRepository.findById(supplierId));
    }

    public ResponseEntity<?> updateSupplier(Long supplierId, SupplierDTO supplierDTO) {

        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found!".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        boolean changes = false;

        Supplier updateSupplier = supplier.get();

        if (supplierDTO.name() != null && !supplierDTO.name().equals(updateSupplier.getName())) {
            updateSupplier.setName(supplierDTO.name());
            changes = true;
        }

        if (supplierDTO.email() != null && !supplierDTO.email().equals(updateSupplier.getEmail())) {
            updateSupplier.setEmail(supplierDTO.email());
            changes = true;
        }

        if (supplierDTO.comment() != null && !supplierDTO.comment().equals(updateSupplier.getComment())) {
            updateSupplier.setComment(supplierDTO.comment());
            changes = true;
        }

        if (supplierDTO.cnpj() != null && !supplierDTO.cnpj().equals(updateSupplier.getCnpj())) {
            updateSupplier.setCnpj(supplierDTO.cnpj());
            changes = true;
        }

        if(changes){
            supplierRepository.save(updateSupplier);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update was not needed!");

    }

    public ResponseEntity deleteSupplierById(Long supplierId) {
        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found!".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        supplierRepository.deleteById(supplierId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
