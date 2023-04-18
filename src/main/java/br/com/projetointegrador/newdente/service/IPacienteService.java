package br.com.projetointegrador.newdente.service;

import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Paciente;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import org.springframework.http.ResponseEntity;

public interface IPacienteService {
    public Iterable<Paciente> listar();

    public Paciente buscarPacientePorId(Long id);

    public ResponseEntity<?> cadastrar(Paciente dentista);

    public ResponseEntity<?> atualizar(Long id, Paciente dentistaAtualizado);

    ResponseEntity<ModeloDeResposta> remover(long id);
}
