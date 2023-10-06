package br.com.alura.codechella.model;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.alura.codechella.controller.IngressoController;
import br.com.alura.codechella.controller.PessoaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pessoa pessoa;

    private String setor;

    private String diaFestival;

    private LocalDateTime dtRegistro;


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