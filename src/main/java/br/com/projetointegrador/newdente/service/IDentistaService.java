package br.com.projetointegrador.newdente.service;

import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import org.springframework.http.ResponseEntity;

public interface IDentistaService {
    public Iterable<Dentista> listar();

    public Dentista buscarDentistaPorId(Long id);

    public ResponseEntity<?> cadastrar(Dentista dentista);

    public ResponseEntity<?> atualizar(Long id, Dentista dentistaAtualizado);

     ResponseEntity<ModeloDeResposta> remover(long id);
}
