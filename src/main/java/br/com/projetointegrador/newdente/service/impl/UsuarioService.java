package br.com.projetointegrador.newdente.service.impl;


import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.dto.UsuarioDTO;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.repository.IUsuarioRepository;
import br.com.projetointegrador.newdente.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ModeloDeResposta modeloDeResposta;

    @Override
    public Iterable<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> cadastrar(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getLogin().isEmpty()) {
            modeloDeResposta.setMensagem("O login é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else if (usuarioDTO.getSenha().isEmpty()) {
            modeloDeResposta.setMensagem("A senha é obrigatória!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else {
            // Verifica se o login já existe no banco
            Usuario usuarioExistente = (Usuario) usuarioRepository.findByLogin(usuarioDTO.getLogin());
            if (usuarioExistente != null) {
                modeloDeResposta.setMensagem("O login já existe!");
                return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCriptografada = encoder.encode(usuarioDTO.getSenha());

            // Cria o objeto Usuario a partir das informações da DTO
            Usuario usuario = new Usuario();
            usuario.setUsuario(usuarioDTO.getUsuario());
            usuario.setLogin(usuarioDTO.getLogin());
            usuario.setSenha(senhaCriptografada);
            usuario.setFuncao(usuarioDTO.getFuncao());

            // Salva o usuário no banco
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
        }
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (!optionalUsuario.isPresent()) {
            modeloDeResposta.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }
        Usuario usuarioEncontrado = optionalUsuario.get();
        usuarioEncontrado.setUsuario(usuarioAtualizado.getUsuario());
        usuarioEncontrado.setLogin(usuarioAtualizado.getLogin());

        // Criptografa a senha
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuarioAtualizado.getSenha());
        usuarioEncontrado.setSenha(senhaCriptografada);

        usuarioEncontrado.setFuncao(usuarioAtualizado.getFuncao());

        Usuario usuarioSalvo = usuarioRepository.save(usuarioEncontrado);
        modeloDeResposta.setMensagem("Usuário atualizado com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ModeloDeResposta> remover(long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (!optionalUsuario.isPresent()) {
            modeloDeResposta.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
        modeloDeResposta.setMensagem("Usuário removido com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }

    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .login(usuarioDTO.getLogin())
                .senha(usuarioDTO.getSenha())
                .build();
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .build();
    }

    public boolean validaUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getLogin() != null &&
                !usuarioDTO.getLogin().isEmpty() &&
                !usuarioDTO.getLogin().isBlank() &&
                usuarioDTO.getSenha() != null &&
                !usuarioDTO.getSenha().isEmpty() &&
                !usuarioDTO.getSenha().isBlank();
    }
}

