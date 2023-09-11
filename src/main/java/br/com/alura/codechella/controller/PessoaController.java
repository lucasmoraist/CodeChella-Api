package br.com.alura.codechella.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import br.com.alura.codechella.model.Pessoa;
import br.com.alura.codechella.model.Request;
import br.com.alura.codechella.model.Response;
import br.com.alura.codechella.repositories.PessoaRepository;

@RestController
@Slf4j
@RequestMapping("/pessoa")
public class PessoaController {
    
    @Autowired
    PessoaRepository repository;
    

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Response> getAll(){

        List<Response> pessoas = repository.findAll().stream().map(Response::new).toList();
        return pessoas;
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> show(@PathVariable Long id){
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
    @PostMapping
    public void reserva(@RequestBody Request data){

        log.info("Cadastrando pessoa" + data);

        Pessoa p = new Pessoa(data); 

        repository.save(p);
        return;
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        repository.findById(id);

        pessoa.setId(id);
        repository.save(pessoa);

        return ResponseEntity.ok(pessoa);

    }   

    @DeleteMapping("{id}")
    public ResponseEntity<Pessoa> destroy(@PathVariable Long id){
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            repository.delete(pessoa);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
