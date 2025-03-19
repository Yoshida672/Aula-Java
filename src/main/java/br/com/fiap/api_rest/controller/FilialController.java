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
@RequestMapping(value="/filiais",produces = {"application/json"})
@Tag(name = "api-clientes")
public class FilialController {
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    FilialService filialService;
    @PostMapping
    public ResponseEntity<Filial> createFilial(@Valid @RequestBody FilialRequest filial){
        Filial filialSalva = filialRepository.save(filialService.requestToFilial(filial));
        return new ResponseEntity<>(filialSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FilialResponse>> readFiliais(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return new ResponseEntity<>(filialService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> readFilial(@PathVariable Long id) {
        Optional<Filial> filial = filialRepository.findById(id);
        return filial.map(value -> new ResponseEntity<>(filialService.filialToResponse(value, false), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> updateFilial(@PathVariable Long id,
                                                 @RequestBody Filial filial) {
        Optional<Filial> filialExistente = filialRepository.findById(id);
        if (filialExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        filial.setId(filialExistente.get().getId());
        Filial filialAtualizado = filialRepository.save(filial);
        return new ResponseEntity<>(filialAtualizado, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id) {
        Optional<Filial> filialExistente = filialRepository.findById(id);
        if (filialExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        filialRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
