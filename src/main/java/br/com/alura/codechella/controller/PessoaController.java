package br.com.alura.codechella.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.codechella.model.Pessoa;
import br.com.alura.codechella.repository.PessoaRepository;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    Logger log = LoggerFactory.getLogger(getClass());

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

    @GetMapping("busca/{cpf}")
    public ResponseEntity<Pessoa> showCpf(@PathVariable Long cpf){
        log.info("buscando pessoa com id " + cpf);
        return ResponseEntity.ok(getCpf(cpf));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Pessoa> show(@PathVariable Long id){
        log.info("buscando pessoa com id " + id);
        return ResponseEntity.ok(getPessoa(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pessoa> destroy(@PathVariable Long id){
        log.info("apagando pessoa com id " + id);
        pessoaRepository.delete(getPessoa(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        log.info("alterando pessoa com id " + id);

        getPessoa(id);
        pessoa.setId(id);
        pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoa);

    }

    private Pessoa getPessoa(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

    private Pessoa getCpf(Long cpf) {
        return pessoaRepository.findByCpf(cpf)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }
}
