package com.kleberaluizio.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity(name = "supplier")
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String comment;
    private String cnpj;

    public Supplier(){};

    public Supplier(SupplierDTO supplierDTO){
        this(
                supplierDTO.name(),
                supplierDTO.email(),
                supplierDTO.comment(),
                supplierDTO.cnpj()
        );
    };

    public Supplier(
            String name,
            String email,
            String comment,
            String cnpj) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
