package br.com.projetointegrador.newdente.controller;


import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Busca todos os usuarios
    @GetMapping("/listar")
    public Iterable<Usuario> listar(){
        return usuarioService.listar();
    }

    //    busca os usuarios por ID para a pagina de detalhes
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //o metodo abaixo cadastra o usuario no banco
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        ResponseEntity<?> response = usuarioService.cadastrar(usuario);
        return response;
    }

    //    o metodo abaixo atualiza as informações do usuario
    @PutMapping("/{id}/editar")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        ResponseEntity<?> response = usuarioService.atualizar(id, usuarioAtualizado);
        return response;
    }

    // o metodo abaixo deleta um usuario por ID
    @DeleteMapping("/{id}/excluir")
    public ResponseEntity<ModeloDeResposta> excluir(@PathVariable Long id) {

        return usuarioService.remover(id);
    }

    @GetMapping("/")
    public String rota(){
        return "Api de usuarios funcionando!";
    }
}