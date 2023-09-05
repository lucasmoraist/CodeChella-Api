package br.com.alura.codechella.model;

import java.time.LocalDate;

public record Response(Long id, String nome, String email, String cpf, String setor, LocalDate dtNasc) {
    
    public Response(Pessoa p){
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf(), p.getSetor(), p.getDtNasc());
    }
}
