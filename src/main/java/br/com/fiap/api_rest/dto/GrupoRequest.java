package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GrupoRequest(@NotNull(message = "O nome é obrigatório") String nome,
                           String descricao,
                           List<Cliente> clientes) {
}
