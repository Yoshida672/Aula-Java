package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.GrupoRequest;
import br.com.fiap.api_rest.dto.GrupoResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Grupo;
import br.com.fiap.api_rest.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GrupoService {
    @Autowired
    GrupoRepository grupoRepository;

    public Grupo requestToGrupo(GrupoRequest grupoRequest){
        return new Grupo(
                null,
                grupoRequest.nome(),
                grupoRequest.descricao(),
                grupoRequest.clientes()
        );
    }
    public GrupoResponse grupoToResponse(Grupo grupo){
        return new GrupoResponse(
                grupo.getId(),
                grupo.getNome(),
                grupo.getDescricao(),
                grupo.getClientes().stream().map(Cliente::getNome).toList().toString());
    }

    public List<GrupoResponse> findAll(){

            return grupoRepository.findAll()
                    .stream()
                    .map(this::grupoToResponse)
                    .collect(Collectors.toList());
        };

}
