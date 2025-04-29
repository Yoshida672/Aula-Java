package br.com.fiap.apisecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthDTO(
       @NotBlank(message = "O nome de usuario não pode ficar vazio") String login,
       @NotBlank(message = "A senha não pode ficar vazia") String password
) {
}
