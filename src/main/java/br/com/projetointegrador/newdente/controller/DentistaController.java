package br.com.projetointegrador.newdente.controller;


import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.service.DentistaService;
import br.com.projetointegrador.newdente.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    //Busca todos os usuarios
    @GetMapping("/listar")
    public Iterable<Dentista> listar(){
        return dentistaService.listar();
    }

    //    busca os dentistas por ID para a pagina de detalhes
    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarPorId(@PathVariable Long id) {
        Dentista dentista = dentistaService.buscarDentistaPorId(id);
        if (dentista != null) {
            return ResponseEntity.ok(dentista);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //o metodo abaixo cadastra o usuario no banco
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarDentista(@RequestBody Dentista dentista) {
        ResponseEntity<?> response = dentistaService.cadastrar(dentista);
        return response;
    }

    //    o metodo abaixo atualiza as informações do dentista
    @PutMapping("/{id}/editar")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Dentista dentistaAtualizado) {
        ResponseEntity<?> response = dentistaService.atualizar(id, dentistaAtualizado);
        return response;
    }

    // o metodo abaixo deleta um dentista por ID
    @DeleteMapping("/{id}/excluir")
    public ResponseEntity<ModeloDeResposta> excluir(@PathVariable Long id) {

        return dentistaService.remover(id);
    }

    @GetMapping("/")
    public String rota(){
        return "Api de dentista funcionando!";
    }
}