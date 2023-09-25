package com.kleberaluizio.api.service;

import com.kleberaluizio.api.model.Supplier;
import com.kleberaluizio.api.model.SupplierDTO;
import com.kleberaluizio.api.repository.SupplierRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity addSupplier(SupplierDTO registrationSupplierDTO){

        // Check if cnpj already exists (This is a unique data for each Supplier)
        String cnpj = registrationSupplierDTO.cnpj();

        if (supplierRepository.existsSupplierByCnpj(cnpj)) {
            String message = "Fornecedor com CNPJ duplicado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Supplier newSupplier = new Supplier(registrationSupplierDTO);

        supplierRepository.save(newSupplier);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<Supplier>>  getAllSuppliers(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    public ResponseEntity<?> getSupplier(Long supplierId){
        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }


        return ResponseEntity.status(HttpStatus.OK).body(supplierRepository.findById(supplierId));
    }

    public ResponseEntity updateSupplier(Long supplierId, SupplierDTO supplierDTO) {

        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found!".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
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
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body("Update was not needed!");

    }

    public ResponseEntity deleteSupplierById(Long supplierId) {
        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found!".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        supplierRepository.deleteById(supplierId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    private ResponseEntity checkValidationErrors(SupplierDTO dto){
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<SupplierDTO>> violations = validator.validate(dto);
//
//        // Check for validation errors
//        if (!violations.isEmpty()) {
//            List<String> errorMessages = new ArrayList<>();
//            for (ConstraintViolation<SupplierDTO> violation : violations) {
//                errorMessages.add(violation.getMessage());
//            }
//            return ResponseEntity.badRequest().body(errorMessages);
//        } else {
//            // Proceed with creating the supplier
//            return ResponseEntity.ok("Supplier created successfully");
//        }
//    }

}
