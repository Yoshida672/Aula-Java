package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import org.springframework.hateoas.Link;

public record FilialResponse(long id, String nome, Endereco endereco, Link link) {
}
