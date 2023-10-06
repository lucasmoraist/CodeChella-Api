package br.com.alura.codechella.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import br.com.alura.codechella.exception.RestNotFoundException;
import br.com.alura.codechella.model.Ingresso;
import br.com.alura.codechella.repository.IngressoRepository;
import br.com.alura.codechella.repository.PessoaRepository;

@RestController
@Slf4j
@RequestMapping("api/ingresso")
public class IngressoController {

    List<Ingresso> ingressos = new ArrayList<>();
    
    @Autowired
    IngressoRepository ingressoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String setor, @PageableDefault(size = 5) Pageable pageable){
        Page<Ingresso> ingressos = (setor == null) ?
            ingressoRepository.findAll(pageable):
            ingressoRepository.findBySetorContaining(setor, pageable);
        
        return assembler.toModel(ingressos.map(Ingresso::toModel));
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do ingresso",
        description = "Retorna os dados de um ingresso especifico"
    )
    public EntityModel<Ingresso> show(@PathVariable Long id){
        log.info("buscando ingresso com id" + id);
        var ingresso = ingressoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Ingresso não encontrado"));
        return ingresso.toModel();
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "ingresso cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "erro na validação de dados da requisição")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Ingresso ingresso){
        
        log.info("cadastrando ingresso: " + ingresso);
        ingressoRepository.save(ingresso);
        ingresso.setDtRegistro(LocalDateTime.now());
        ingresso.setPessoa(pessoaRepository.findById(ingresso.getPessoa().getId()).get());
        
        return ResponseEntity
            .created(ingresso.toModel().getRequiredLink("self").toUri())
            .body(ingresso.toModel());
    }

    @PutMapping("{id}")
    public EntityModel<Ingresso> update(@PathVariable Long id, @RequestBody @Valid Ingresso ingresso){
        log.info("alterando ingresso com id " + id);
        ingressoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("ingresso não encontrado"));
        
        ingresso.setId(id);
        ingressoRepository.save(ingresso);

        return ingresso.toModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ingresso> destroy(@PathVariable Long id){
        log.info("excluindo ingresso com id "+id);
        var ingresso = ingressoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("ingresso não encontrado"));
        
        ingressoRepository.delete(ingresso);

        return ResponseEntity.noContent().build();
    }
}