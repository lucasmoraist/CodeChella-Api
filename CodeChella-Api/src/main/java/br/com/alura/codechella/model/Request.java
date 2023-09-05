package br.com.alura.codechella.model;

import java.time.LocalDate;

public record Request(String nome, String email, String cpf, String setor, LocalDate dtNasc) {
    
}
