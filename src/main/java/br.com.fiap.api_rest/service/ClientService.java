package br.com.fiap.api_rest.service;


import br.com.fiap.api_rest.dto.ClientRequest;
import br.com.fiap.api_rest.dto.ClientResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
public ClientResponse clientToResponse(Cliente cliente){
    return  new ClientResponse(cliente.getId(), cliente.getNome(),cliente.getCategoria());
}
public List<ClientResponse> clientsToResponse(List<Cliente> clientes){
    return clientes.stream().map(this::clientToResponse).collect(Collectors.toList());
}
public Page findAll(Pageable pageable){
    return clienteRepository.findAll(pageable).map(this::clientToResponse);
}
}
