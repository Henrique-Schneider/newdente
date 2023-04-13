package br.com.projetointegrador.newdente.repository;

import br.com.projetointegrador.newdente.models.Consulta;
import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Long> {
    @Query("SELECT d FROM Dentista d")
    public List<Dentista> findAllDentistas();

    @Query("SELECT p FROM Paciente p")
    public List<Paciente> findAllPacientes();

}
