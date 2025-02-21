package br.com.fiap.api_rest.service;


import br.com.fiap.api_rest.dto.ClientRequest;
import br.com.fiap.api_rest.dto.ClientResponse;
import br.com.fiap.api_rest.model.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
public Cliente requestToCliente(ClientRequest clientRequest){
    Cliente cliente = new Cliente();
    BeanUtils.copyProperties(clientRequest,cliente);
    return cliente;
}
public ClientResponse clientToResponse(Cliente cliente){
    return  new ClientResponse(cliente.getId(), cliente.getNome());
}
public List<ClientResponse> clientsToResponse(List<Cliente> clientes){
    return clientes.stream().map(this::clientToResponse).collect(Collectors.toList());
}
}
