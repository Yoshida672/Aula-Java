package br.com.fiap.apisecurity.dto;

import br.com.fiap.apisecurity.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank(message = "login não pode ficar vazio") String login,
        @NotBlank(message = "senha não pode ficar vazia") String password,
        @NotNull(message = "Role nao pode ser nula") UserRole role
        ) {

}
