package br.com.fiap.api_rest.service;


import br.com.fiap.api_rest.controller.ClienteController;
import br.com.fiap.api_rest.dto.ClientRequest;
import br.com.fiap.api_rest.dto.ClientResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClientService {
    @Autowired
    ClienteRepository clienteRepository;
public Cliente requestToCliente(ClientRequest clientRequest){
   return new Cliente(null, clientRequest.getNome(),
            clientRequest.getIdade(),
            clientRequest.getEmail(),
            clientRequest.getSenha(),
            clientRequest.getCpf(),
            clientRequest.getCategoria() );
}
    public ClientResponse clientToResponse(Cliente cliente, boolean self) {
        Link link;
        if (self) {
            link = linkTo(
                    methodOn(
                            ClienteController.class
                    ).readCliente(cliente.getId())
            ).withSelfRel();
        } else {
            link = linkTo(
                    methodOn(
                            ClienteController.class
                    ).readClientes(0)
            ).withRel("Lista de Clientes");
        }
        return new ClientResponse(cliente.getId(), cliente.getNome(), cliente.getCategoria(),link);
    }

    public List<ClientResponse> clientesToResponse(List<Cliente> clientes) {
        List<ClientResponse> clientesResponse = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesResponse.add(clientToResponse(cliente, true));
        }
        return clientesResponse;
        // return clientes.stream().map(this::clienteToResponse).collect(Collectors.toList());
    }
    public Page<ClientResponse> findAll(Pageable pageable) {
        // busca os clientes de acordo com a configuração do pageable,
        // converte para response e retorna como um Page<ClienteResponse>
        return clienteRepository.findAll(pageable)
                .map(cliente -> clientToResponse(cliente, true));
        //return clienteRepository.findAll(pageable).map(this::clienteToResponse);
    }
}
