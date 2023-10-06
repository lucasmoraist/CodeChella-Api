package br.com.alura.codechella.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "T_PESSOA")
public class Pessoa { 

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    
    private String cpf;

    private String setor;

    private LocalDate dtNasc;

    @Override
    public String toString(){
        return "Pessoa [Nome: " +nome+ "\nEmail: " +email+ "\nCpf: " +cpf+ "\nSetor: " +setor+ "\nData de nascimento: " +dtNasc+ "]";
    }
}
