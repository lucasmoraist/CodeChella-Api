package br.com.alura.codechella.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Table(name = "pessoa")
@Entity
@EqualsAndHashCode(of = "id")
public class Pessoa { 

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    
    private String cpf;

    private String setor;

    private int idade;

    public Pessoa(Request data){
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.setor = data.setor();
        this.idade = data.idade();
    }
}
