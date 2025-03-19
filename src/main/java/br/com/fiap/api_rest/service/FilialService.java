package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.controller.ClienteController;
import br.com.fiap.api_rest.controller.FilialController;
import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.EnderecoRepository;
import br.com.fiap.api_rest.repository.FilialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service

public class FilialService {
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    EnderecoRepository enderecoRepository;


    public Filial requestToFilial(FilialRequest filialRequest){

        return new Filial(
                null,filialRequest.nome(),filialRequest.endereco(),null
        );
    }
    public FilialResponse filialToResponse(Filial filial, boolean self) {
        Link link;
        if(self){
            link = linkTo(methodOn(FilialController.class).readFilial(filial.getId())).withSelfRel().withRel("Filial");
        }else{
            link =linkTo(
                    methodOn(
                            FilialController.class
                    ).readFiliais(0)
            ).withRel("Lista de Filiais");
        }
        return  new FilialResponse(
                filial.getId(),
                filial.getNome(),
                filial.getEndereco()
                ,link);
    }


    public Page<FilialResponse> findAll(Pageable pageable){
        return filialRepository.findAll(pageable).map(filial -> filialToResponse(filial,true));
    }

}
