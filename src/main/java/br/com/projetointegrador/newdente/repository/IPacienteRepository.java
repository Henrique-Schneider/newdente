package br.com.projetointegrador.newdente.repository;


import br.com.projetointegrador.newdente.models.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPacienteRepository extends CrudRepository<Paciente, Long> {
    Optional<Paciente> findById(Long id);
    Paciente findByRg(String rg);
}
