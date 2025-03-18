package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Categoria;
import org.springframework.hateoas.Link;

public record ClienteResponse(long id, String nome, Categoria categoria, Link link){

}
