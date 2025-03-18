package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.FilialRepository;
import br.com.fiap.api_rest.service.FilialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
