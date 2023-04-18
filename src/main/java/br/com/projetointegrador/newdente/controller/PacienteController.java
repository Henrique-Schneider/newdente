package br.com.projetointegrador.newdente.controller;

import br.com.projetointegrador.newdente.models.Paciente;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //Busca todos os pacientes
    @GetMapping("/listar")
    public Iterable<Paciente> listar(){
        return pacienteService.listar();
    }

    //Busca o paciente por ID para a pagina de detalhes
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra um novo paciente no banco
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<?> response = pacienteService.cadastrar(paciente);
        return response;
    }

    //Atualiza as informações do paciente
    @PutMapping("/{id}/editar")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        ResponseEntity<?> response = pacienteService.atualizar(id, pacienteAtualizado);
        return response;
    }

    //Deleta um paciente por ID
    @DeleteMapping("/{id}/excluir")
    public ResponseEntity<ModeloDeResposta> excluir(@PathVariable Long id) {
        return pacienteService.remover(id);
    }

    @GetMapping("/")
    public String rota(){
        return "API de pacientes funcionando!";
    }
}
