package br.com.projetointegrador.newdente.service;

import br.com.projetointegrador.newdente.models.Consulta;
import br.com.projetointegrador.newdente.models.dto.ConsultaDTO;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import org.springframework.http.ResponseEntity;

public interface IConsultaService {

    Iterable<Consulta> listar();
    Consulta buscarConsultaPorId(Long id);
    ResponseEntity<?> cadastrar(Consulta consulta);
    ResponseEntity<?> atualizar(Long id, Consulta consultaAtualizada);
    ResponseEntity<ModeloDeResposta> remover(Long id);
}
