package br.com.projetointegrador.newdente.controller;

import br.com.projetointegrador.newdente.models.Consulta;

import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.service.impl.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    // Busca todas as consultas
    @GetMapping("/listar")
    public Iterable<Consulta> listar() {
        return consultaService.listar();
    }

    // Busca a consulta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cadastra uma nova consulta
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarConsulta(@RequestBody Consulta consulta) {
        ResponseEntity<?> response = consultaService.cadastrar(consulta);
        return response;
    }

    // Atualiza uma consulta existente
    // Alteração na controller:
    @PutMapping("/{id}/editar")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        Consulta consultaExistente = consultaService.buscarConsultaPorId(id);
        if (consultaExistente != null) {
            consultaAtualizada.setId(id);
            consultaService.cadastrar(consultaAtualizada);
            return ResponseEntity.ok(consultaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @PutMapping("/{id}/editar")
//    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
//        ResponseEntity<?> response = consultaService.atualizar(id, consultaAtualizada);
//        return response;
//    }

    // Remove uma consulta por ID
    @DeleteMapping("/{id}/excluir")
    public ResponseEntity<ModeloDeResposta> excluir(@PathVariable Long id) {
        return consultaService.remover(id);
    }

    // Retorna uma mensagem simples para validar a rota
    @GetMapping("/")
    public String rota() {
        return "API de consultas funcionando!";
    }
}
