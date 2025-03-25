package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.GrupoRequest;
import br.com.fiap.api_rest.dto.GrupoResponse;
import br.com.fiap.api_rest.model.Grupo;
import br.com.fiap.api_rest.repository.GrupoRepository;
import br.com.fiap.api_rest.service.GrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/grupos",produces = {"application/json"})
@Tag(name = "api-grupos")
public class GrupoController {
    @Autowired
    GrupoRepository grupoRepository;
    @Autowired
    GrupoService grupoService;
@GetMapping
    public ResponseEntity<List<GrupoResponse>> getGrupos(){
    return new ResponseEntity<>(grupoService.findAll(),HttpStatus.CREATED);
}
@GetMapping("/{id}")
    public ResponseEntity<GrupoResponse> getGrupo(@PathVariable Long id){
     Optional<Grupo> grupo =grupoRepository.findById(id);
    return grupo.map(value->new ResponseEntity<>(grupoService.grupoToResponse(value),HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

}
@PostMapping
    public ResponseEntity<GrupoResponse> createGrupo(@Valid @RequestBody GrupoRequest grupoRequest){
    Grupo grupo = grupoRepository.save(grupoService.requestToGrupo(grupoRequest));
    return new ResponseEntity<>(grupoService.grupoToResponse(grupo),HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<GrupoResponse> updateGrupo(@PathVariable Long id,
                                                 @RequestBody Grupo grupo){
    Optional<Grupo> grupoExistente = grupoRepository.findById(id);
    if (grupoExistente.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
   grupo.setId(grupoExistente.get().getId());
   Grupo grupoAtualizado = grupoRepository.save(grupo);
   return new ResponseEntity<>(grupoService.grupoToResponse(grupoAtualizado),HttpStatus.OK);
}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Long id){
    Optional<Grupo> grupoExistente = grupoRepository.findById(id);
    if (grupoExistente.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    grupoRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

}

}
