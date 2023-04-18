package br.com.projetointegrador.newdente.service;

import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.dto.UsuarioDTO;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {
    Iterable<Usuario> listar();
    Usuario buscarUsuarioPorId(Long id);
    ResponseEntity<?> cadastrar(UsuarioDTO usuario);
    ResponseEntity<?> atualizar(Long id, Usuario usuarioAtualizado);
    ResponseEntity<ModeloDeResposta> remover(long id);

}
