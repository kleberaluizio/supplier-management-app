package com.kleberaluizio.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank
    private String name;
    @Email(message = "Not valid")
    private String email;
    private String comment;
    @Pattern(regexp = "^\\d{2}\\.\\d{3}.\\d{3}/\\d{4}-\\d{2}$")
    private String cnpj;

    public Supplier(){};

    public Supplier(
            @NotBlank String name,
            @Email(message = "Not valid") String email,
            String comment,
            @Pattern(regexp = "^\\d{2}\\.\\d{3}.\\d{3}/\\d{4}-\\d{2}$") String cnpj) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.cnpj = cnpj;
    }

    public int getId() {
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
}
