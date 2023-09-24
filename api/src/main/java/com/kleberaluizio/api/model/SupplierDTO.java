package com.kleberaluizio.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record SupplierDTO(
        String name,
        @Email(message = "Not valid")
        String email,
        String comment,
        @Pattern(regexp = "^\\d{2}\\.\\d{3}.\\d{3}/\\d{4}-\\d{2}$",
        message = "QUE PORRA É ESSA?")
        String cnpj
) {
}
