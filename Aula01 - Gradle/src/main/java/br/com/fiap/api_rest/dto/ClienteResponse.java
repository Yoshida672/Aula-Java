package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Categoria;
import br.com.fiap.api_rest.model.Filial;
import org.springframework.hateoas.Link;

public record ClienteResponse(long id, String nome, Categoria categoria, Filial filial, Link link){

}
