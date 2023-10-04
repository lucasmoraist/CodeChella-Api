package br.com.alura.codechella.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.codechella.exception.RestNotFoundException;
import br.com.alura.codechella.model.Pessoa;
import br.com.alura.codechella.repository.PessoaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/pessoa")
public class PessoaController {
    List<Pessoa> pessoas = new ArrayList<>();

    @Autowired // IoD - Injeção de dependência
    PessoaRepository pessoaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Pessoa> index(){
        return pessoaRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Pessoa pessoa){
        log.info("cadastrando pessoa: " + pessoa);
        pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Pessoa> show(@PathVariable Long id){
        log.info("buscando pessoa com id " + id);
        var pessoa = pessoaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Pessoa não encontrado"));
        return ResponseEntity.ok(pessoa);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pessoa> destroy(@PathVariable Long id){
        log.info("apagando pessoa com id " + id);
        var pessoa = pessoaRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("Pessoa não encontrado"));

        pessoaRepository.delete(pessoa);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        log.info("alterando pessoa com id " + id);
        pessoaRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("pessoa não encontrado"));

        pessoa.setId(id);
        pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoa);

    }   
}
