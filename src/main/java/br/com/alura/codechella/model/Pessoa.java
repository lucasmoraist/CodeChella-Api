package br.com.alura.codechella.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Pessoa { 

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    
    private String cpf;


    private LocalDate dtNasc;

    @Override
    public String toString(){
        return "Pessoa [Nome: " +nome+ "\nEmail: " +email+ "\nCpf: " +cpf+ "\nData de nascimento: " +dtNasc+ "]";
    }
}
