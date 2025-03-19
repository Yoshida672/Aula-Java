package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.controller.EnderecoController;
import br.com.fiap.api_rest.controller.FilialController;
import br.com.fiap.api_rest.dto.EnderecoRequest;
import br.com.fiap.api_rest.dto.EnderecoResponse;
import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.model.Endereco;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.EnderecoRepository;
import br.com.fiap.api_rest.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;


    public Endereco requestToEndereco(EnderecoRequest enderecoRequest){
        return  new Endereco(null,
                enderecoRequest.getLocalizacao(),
                enderecoRequest.getFilial());

    }
    public EnderecoResponse enderecoToResponse(Endereco endereco, boolean self) {
        Link link;
        if(self){
            link = linkTo(methodOn(EnderecoController.class).readEndereco(endereco.getId())).withSelfRel().withRel("Endereco");
        }else{
            link =linkTo(
                    methodOn(
                            EnderecoController.class
                    ).readEnderecos(0)
            ).withRel("Lista de Endereços");
        }
        return  new EnderecoResponse(
                endereco.getId(),
                endereco.getLocalizacao(),
                endereco.getFilial()
                ,link);
    }


    public Page<EnderecoResponse> findAll(Pageable pageable){
        return enderecoRepository.findAll(pageable).map(endereco -> enderecoToResponse(endereco,true));
    }

}
