package org.fede.springjpa.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "Firstname should not be empty")
        String firstname,
        @NotEmpty(message = "Firstname should not be empty")
        String lastname,
        String gmail,
        Integer schoolId
) {
}
