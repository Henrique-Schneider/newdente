package br.com.projetointegrador.newdente.controller;


import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.dto.UsuarioDTO;
import br.com.projetointegrador.newdente.security.TokenDTO;
import br.com.projetointegrador.newdente.security.TokenService;

import br.com.projetointegrador.newdente.service.impl.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> logar(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getLogin() == null || usuarioDTO.getLogin().isEmpty() ||
                usuarioDTO.getSenha() == null || usuarioDTO.getSenha().isEmpty()) {
            // Retorne uma resposta de erro indicando que os campos são obrigatórios
            return ResponseEntity.badRequest().body(new TokenDTO("Nome de usuário e senha são obrigatórios"));
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(usuarioDTO.getLogin(), usuarioDTO.getSenha());
        Authentication authenticate = manager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarUsuario( @RequestBody UsuarioDTO usuario) {
        ResponseEntity<?> response = usuarioService.cadastrar(usuario);
        return ResponseEntity.status(response.getStatusCode()).build();
    }
}