package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import org.springframework.hateoas.Link;

import java.util.List;

public record FilialResponse(long id, String nome, String endereco, List<String> cientes) {
}
