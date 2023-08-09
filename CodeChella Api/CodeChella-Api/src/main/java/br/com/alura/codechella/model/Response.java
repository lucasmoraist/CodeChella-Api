package br.com.alura.codechella.model;

public record Response(Long id, String nome, String email, String cpf, String setor, int idade) {
    
    public Response(Pessoa p){
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf(), p.getSetor(), p.getIdade());
    }
}
