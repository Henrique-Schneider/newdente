package br.com.projetointegrador.newdente.repository;

import br.com.projetointegrador.newdente.models.Consulta;
import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsultaRepository extends CrudRepository<Consulta, Long> {


}
