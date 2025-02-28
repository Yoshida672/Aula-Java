package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.ClientRequest;
import br.com.fiap.api_rest.dto.ClientResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import br.com.fiap.api_rest.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClientService clientService;
    // Create, Read, Update, Delete
    // Post, Get, Put, Delete

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClientRequest cliente) {
        Cliente clienteSalvo = clienteRepository.save(clientService.requestToCliente(cliente));
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> readClientes(@RequestParam(defaultValue = "0") Integer page) {
        Pageable  pageable = PageRequest.of( page,2, Sort.by("nome").ascending().and(Sort.by("categoria")));
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }
    //PathVariable parametro na url ex: /clientes/1
    //Requestparam parametro como query ex: /clientes/?id=1
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> readCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(value -> new ResponseEntity<>(clientService.clientToResponse(value,false), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,
                                                 @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente.setId(clienteExistente.get().getId());
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
