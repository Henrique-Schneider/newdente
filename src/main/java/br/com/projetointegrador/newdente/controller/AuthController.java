package br.com.projetointegrador.newdente.controller;

import br.com.projetointegrador.newdente.models.Usuario;

import br.com.projetointegrador.newdente.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class AuthController {
@Autowired
private UsuarioRepository usuarioRepository;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Busca o usuário com o login informado
        Usuario usuarioBD = usuarioRepository.findByLogin(usuario.getLogin());

        if (usuarioBD != null && usuarioBD.getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok("Usuário logado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }
}