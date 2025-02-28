package br.com.fiap.api_rest.service;


import br.com.fiap.api_rest.controller.ClienteController;
import br.com.fiap.api_rest.dto.ClientRequest;
import br.com.fiap.api_rest.dto.ClientResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClientService {
    @Autowired
    ClienteRepository clienteRepository;
public Cliente requestToCliente(ClientRequest clientRequest){
    Cliente cliente = new Cliente();

   return new Cliente(null, clientRequest.getNome(),
            clientRequest.getIdade(),
            clientRequest.getEmail(),
            clientRequest.getSenha(),
            clientRequest.getCpf(),
            clientRequest.getCategoria() );
}
public ClientResponse clientToResponse(Cliente cliente,boolean self) {
    Link link;
    if(self){
    link = linkTo(methodOn(ClienteController.class).readCliente(cliente.getId())).withSelfRel().withRel("Cliente");
}else{
     link =linkTo(
            methodOn(
                    ClienteController.class
            ).readClientes(0)
    ).withRel("Lista de Clientes");
}
    return  new ClientResponse(cliente.getId(), cliente.getNome(),cliente.getCategoria(),link);
}


public Page<ClientResponse> findAll(Pageable pageable){
      return clienteRepository.findAll(pageable).map(cliente -> clientToResponse(cliente,true));


    //   return clienteRepository.findAll(pageable).map(this::clientToResponse);
}
}
