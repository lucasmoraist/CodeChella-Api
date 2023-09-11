package br.com.alura.codechella.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.codechella.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
