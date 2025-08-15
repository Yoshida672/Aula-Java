package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.*;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.EnderecoRepository;
import br.com.fiap.api_rest.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service

public class FilialService {
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    EnderecoRepository enderecoRepository;
    private FilialResponse filialToReponse(Filial filial){
        return new FilialResponse(
                filial.getId(),
                filial.getNome(),
                filial.getEndereco().getLocalizacao(),
                filial.getClientes().stream().map(Cliente::getNome).collect(Collectors.toList())
        );
        }

    public FilialResponse create( FilialRequest filialRequest){
        Endereco endereco = new Endereco();
        if (filialRequest.getEndereco()!=null){
            endereco = enderecoRepository.save(filialRequest.getEndereco());
        }
        Filial filialSalva = new Filial(filialRequest.getNome(),endereco);
        filialSalva=filialRepository.save(filialSalva);
        return filialToReponse(filialSalva);
    }
    public FilialResponse findById(Long id) {
        Optional<Filial> filial =filialRepository.findById(id);
        return filial.map(this::filialToReponse).orElse(null);
    }
    public List<FilialResponse> findAll(){
       List<Filial> filiais = filialRepository.findAll();
       List<FilialResponse> filialResponses=new ArrayList<>();;
       for(Filial filial:filiais){
           filialResponses.add(filialToReponse(filial));
       }
    return filialResponses;
    }

    public FilialResponse update(Long id,FilialRequest filialRequest){
        Optional<Filial> filial = filialRepository.findById(id);
        if (filial.isEmpty()){
        return null;
        }
        Endereco endereco = new Endereco();
        filial.get().setEndereco(endereco);
        enderecoRepository.save(endereco);
        filial.get().setNome(filialRequest.getNome());
        return filialToReponse(filialRepository.save(filial.get()));
    }
    public boolean delete(Long id,FilialRequest filialRequest){
        Optional<Filial> filial = filialRepository.findById(id);
        if (filial.isEmpty()){
            return false;
        }
        filialRepository.delete(filial.get());
        return true;
    }
}
