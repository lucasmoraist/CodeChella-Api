package br.com.alura.codechella.model;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.alura.codechella.controller.IngressoController;
import br.com.alura.codechella.controller.PessoaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nm_pessoa")
    private Pessoa pessoa;

    private String setor;

    private LocalDate dtRegistro;

    @Override
    public String toString(){
        return String.format("Ingresso [Id: %s \nNome no ingresso: %s \nSetor: %s \nData da Reserva %s]", id, pessoa.getNome(), setor, dtRegistro);
    }

    public EntityModel<Ingresso> toModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(IngressoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(IngressoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(IngressoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(PessoaController.class).show(this.getPessoa().getId())).withRel("pessoa")
        );
    }

}
