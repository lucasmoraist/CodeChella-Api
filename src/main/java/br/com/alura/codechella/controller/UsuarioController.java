package br.com.alura.codechella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.alura.codechella.model.Credencial;
import br.com.alura.codechella.model.Usuario;
import br.com.alura.codechella.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        return ResponseEntity.ok().build();
    }
}
