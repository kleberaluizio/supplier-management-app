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

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplier(Long id){
        return supplierRepository.findById(id);
    }

    public ResponseEntity<?> addSupplier(SupplierDTO registrationSupplierDTO){

        // Check if cnpj already exists
        String cnpj = registrationSupplierDTO.cnpj();
        if (supplierRepository.existsSupplierByCnpj(cnpj)) {
            String message = "Fornecedor com CNPJ duplicado";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Supplier newSupplier = new Supplier(
                registrationSupplierDTO.name(),
                registrationSupplierDTO.email(),
                registrationSupplierDTO.comment(),
                registrationSupplierDTO.cnpj()
        );

        supplierRepository.save(newSupplier);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    public ResponseEntity<?> deleteSupplierById(Long supplierId) {
        // Checks if supplier id exists
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            String message = "Supplier with id [%s] not found".formatted(supplierId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        supplierRepository.deleteById(supplierId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

//    public ResponseEntity<?> updateCustomer(Long id, SupplierDTO updateSupplierRequest){
//
//        // Check if id exists
//        Supplier customer = getSu(customerId);
//
//        boolean changes = false;
//
//        //update
//        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
//            customer.setName(updateRequest.name());
//            changes = true;
//        }
//        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
//            customer.setAge(updateRequest.age());
//            changes = true;
//        }
//
//        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
//            if (customerDao.existsPersonWithEmail(updateRequest.email())) {
//                throw new DuplicateResourceException("Email already taken");
//            }
//            customer.setEmail(updateRequest.email());
//            changes = true;
//        }
//
//        if(!changes) {
//            throw new RequestValidationException("no data changes found");
//        }
//
//    }
}
