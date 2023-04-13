package br.com.projetointegrador.newdente.repository;

import br.com.projetointegrador.newdente.models.Dentista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends CrudRepository<Dentista, Long> {
    Optional<Dentista> findById(Long id);
    Dentista findByMatricula(String matricula);

}
