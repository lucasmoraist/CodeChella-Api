package br.com.alura.codechella.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.codechella.model.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
    
    Page<Ingresso> findBySetorContaining(String setor, Pageable pageable);

}
