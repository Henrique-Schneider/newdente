package br.com.projetointegrador.newdente.controller;



import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.dto.UsuarioDTO;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
@PreAuthorize("hasRole('USER')")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
    public ResponseEntity<?> cadastrarDentista(@RequestBody UsuarioDTO usuario) {
        ResponseEntity<?> response = usuarioService.cadastrar(usuario);
        return response;
    }


    @PutMapping("/{id}/editar")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        ResponseEntity<?> response = usuarioService.atualizar(id, usuarioAtualizado);
        return response;
    }


    @DeleteMapping("/{id}/excluir")
    public ResponseEntity<ModeloDeResposta> excluir(@PathVariable Long id) {

        return usuarioService.remover(id);
    }

    @GetMapping("/")
    public String rota(){
        return "Api de dentista funcionando!";
    }
}