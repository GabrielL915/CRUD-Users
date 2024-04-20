package com.estudoTestes.api.domain.dto;

public record UserDTO(
        Integer id,

        String name,

        String email,

        String password
) {
}
