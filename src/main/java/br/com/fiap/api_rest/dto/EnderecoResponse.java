package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Filial;
import org.springframework.hateoas.Link;

public record EnderecoResponse(Long id, String localizacao, Filial filial, Link link) {
}
