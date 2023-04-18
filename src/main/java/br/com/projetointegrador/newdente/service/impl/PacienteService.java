package br.com.projetointegrador.newdente.service.impl;

import br.com.projetointegrador.newdente.models.Paciente;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.repository.IPacienteRepository;
import br.com.projetointegrador.newdente.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private ModeloDeResposta modeloDeResposta;

    // método para listar todos os pacientes
    @Override
    public Iterable<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    // método para buscar paciente por ID
    @Override
    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    // Metodo para cadastrar paciente
    @Override
    public ResponseEntity<?> cadastrar(Paciente paciente) {
        if (paciente.getNome().equals("")) {
            modeloDeResposta.setMensagem("O nome é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else if (paciente.getSobrenome().equals("")) {
            modeloDeResposta.setMensagem("O sobrenome é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else if (paciente.getRg().equals("")) {
            modeloDeResposta.setMensagem("O RG é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else {
            // Verifica se o paciente já existe no banco
            Paciente pacienteExistente = pacienteRepository.findByRg(paciente.getRg());
            if (pacienteExistente != null) {
                modeloDeResposta.setMensagem("O paciente já está cadastrado!");
                return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            // Salva o paciente no banco
            Paciente pacienteSalvo = pacienteRepository.save(paciente);
            return new ResponseEntity<Paciente>(pacienteSalvo, HttpStatus.CREATED);
        }
    }

    // método para editar paciente
    @Override
    public ResponseEntity<?> atualizar(Long id, Paciente pacienteAtualizado) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (!optionalPaciente.isPresent()) {
            modeloDeResposta.setMensagem("Paciente não encontrado!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }
        Paciente pacienteEncontrado = optionalPaciente.get();
        pacienteEncontrado.setNome(pacienteAtualizado.getNome());
        pacienteEncontrado.setSobrenome(pacienteAtualizado.getSobrenome());
        pacienteEncontrado.setRg(pacienteAtualizado.getRg());
        pacienteEncontrado.setDataAlta(pacienteAtualizado.getDataAlta());
        pacienteEncontrado.setEndereco(pacienteAtualizado.getEndereco());

        Paciente pacienteSalvo = pacienteRepository.save(pacienteEncontrado);
        modeloDeResposta.setMensagem("Paciente atualizado com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }

    // método para excluir paciente por ID
    @Override
    public ResponseEntity<ModeloDeResposta> remover(long id){
        pacienteRepository.deleteById(id);
        modeloDeResposta.setMensagem("Paciente removido com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta,HttpStatus.OK);
    }

}


