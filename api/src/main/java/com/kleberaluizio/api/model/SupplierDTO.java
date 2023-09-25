package com.kleberaluizio.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SupplierDTO(
        @NotBlank(message = "Name field is required. Please provide a value.")
        String name,
        @Email(message = "Invalid email format. Please provide a valid email address.")
        String email,
        String comment,
        @Pattern(regexp = "^\\d{2}\\.\\d{3}.\\d{3}/\\d{4}-\\d{2}$",
        message = "Invalid cnpj format. Please provide a valid cnpj number.")
        String cnpj
) {
}
