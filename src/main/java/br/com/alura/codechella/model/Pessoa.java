package br.com.alura.codechella.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
