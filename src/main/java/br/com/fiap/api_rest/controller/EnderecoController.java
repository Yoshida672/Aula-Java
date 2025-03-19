package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.EnderecoRequest;
import br.com.fiap.api_rest.dto.EnderecoResponse;
import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.model.Endereco;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.EnderecoRepository;
import br.com.fiap.api_rest.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value="/enderecos",produces = {"application/json"})
@Tag(name = "api-clientes")
public class EnderecoController {
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoService enderecoService;
    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@Valid @RequestBody EnderecoRequest endereco){
        Endereco enderecoSalvo = enderecoRepository.save(enderecoService.requestToEndereco(endereco));
        return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoResponse>> readEnderecos(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return new ResponseEntity<>(enderecoService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> readEndereco(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.map(value -> new ResponseEntity<>(enderecoService.enderecoToResponse(value, false), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id,
                                               @RequestBody Endereco endereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        endereco.setId(enderecoExistente.get().getId());
        Endereco enderecoAtualizado = enderecoRepository.save(endereco);
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        enderecoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
