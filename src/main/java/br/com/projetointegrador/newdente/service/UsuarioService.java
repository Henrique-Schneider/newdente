package br.com.projetointegrador.newdente.service;


import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModeloDeResposta modeloDeResposta;

    // metodo para listar todos os usuarios;
    public Iterable<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // metodo para buscar por ID
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Metodo para cadastrar ou editar usuarios
    public ResponseEntity<?> cadastrar(Usuario usuario) {
        if (usuario.getLogin().equals("")) {
            modeloDeResposta.setMensagem("O login é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else if (usuario.getSenha().equals("")) {
            modeloDeResposta.setMensagem("A senha é obrigatória!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else {
            // Verifica se o login já existe no banco
            Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
            if (usuarioExistente != null) {
                modeloDeResposta.setMensagem("O login já existe!");
                return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
            }

            // Salva o usuário no banco
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
        }


    }

    //  Metodo para  editar usuarios
    public ResponseEntity<?> atualizar(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (!optionalUsuario.isPresent()) {
            modeloDeResposta.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }
        Usuario usuarioEncontrado = optionalUsuario.get();
        usuarioEncontrado.setUsuario(usuarioAtualizado.getUsuario());
        usuarioEncontrado.setLogin(usuarioAtualizado.getLogin());
        usuarioEncontrado.setSenha(usuarioAtualizado.getSenha());
        usuarioEncontrado.setFuncao(usuarioAtualizado.getFuncao());

        Usuario usuarioSalvo = usuarioRepository.save(usuarioEncontrado);
        modeloDeResposta.setMensagem("Usuário atualizado com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }

    //    Metodo para excluir por ID
   public ResponseEntity<ModeloDeResposta> remover(long id){
        usuarioRepository.deleteById(id);
        modeloDeResposta.setMensagem("usuario removido com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta,HttpStatus.OK);
   }

}