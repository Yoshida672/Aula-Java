package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.FilialRepository;
import br.com.fiap.api_rest.service.FilialService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/filiais", produces = {"application/json"})
@Tag(name = "api-clientes")
public class FilialController {

    @Autowired
    FilialService filialService;

    @PostMapping
    public ResponseEntity<FilialResponse> createFilial(@Valid @RequestBody FilialRequest filialRequest) {
        return new ResponseEntity<>(filialService.create(filialRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> getFilial(@PathVariable Long id) {
        return new ResponseEntity<>(filialService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilialResponse>> getFiliais(FilialRequest filialRequest) {
        return new ResponseEntity<>(filialService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponse> updateFilial(@PathVariable Long id, FilialRequest filialRequest) {
        return new ResponseEntity<>(filialService.update(id, filialRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id, FilialRequest filialRequest) {
        if (filialService.delete(id, filialRequest)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
