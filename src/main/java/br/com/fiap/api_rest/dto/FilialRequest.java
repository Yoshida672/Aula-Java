package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FilialRequest(@NotBlank(message = "O nome é obrigatório")String nome, @NotNull(message = "O endereço é obrigatório") Endereco endereco
                            ) {
}
