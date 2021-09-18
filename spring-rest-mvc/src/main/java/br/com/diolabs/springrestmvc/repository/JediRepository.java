package br.com.diolabs.springrestmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diolabs.springrestmvc.model.Jedi;

@Repository
public interface JediRepository extends JpaRepository<Jedi, Long>
{

   List<Jedi> findByNomeContainingIgnoreCase(final String nome);
    
}