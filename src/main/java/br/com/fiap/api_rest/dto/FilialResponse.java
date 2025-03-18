package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;

public record FilialResponse(long id, String nome,Endereco endereco) {
}
