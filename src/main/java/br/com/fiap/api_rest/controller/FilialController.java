package br.com.fiap.api_rest.controller;


import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.service.FilialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/filiais", produces = {"application/json"})
@Tag(name = "api-filiais")
public class FilialController {

    @Autowired
    FilialService filialService;

    @PostMapping
    public ResponseEntity<FilialResponse> createFilial(@Valid @RequestBody FilialRequest filialRequest) {
        return new ResponseEntity<>(filialService.create(filialRequest), HttpStatus.CREATED);}

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> readFilial(@PathVariable Long id) {
        return new ResponseEntity<>(filialService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilialResponse>> readFiliais() {
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
