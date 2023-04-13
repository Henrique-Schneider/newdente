package br.com.projetointegrador.newdente.service;

import br.com.projetointegrador.newdente.models.Consulta;
import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Paciente;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.repository.ConsultaRepository;
import br.com.projetointegrador.newdente.repository.DentistaRepository;
import br.com.projetointegrador.newdente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModeloDeResposta modeloDeResposta;

    public Iterable<Consulta> listar() {
        return consultaRepository.findAll();
    }

    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> cadastrar(Consulta consulta) {
        Optional<Dentista> optionalDentista = dentistaRepository.findById(consulta.getDentista().getId());
        if (!optionalDentista.isPresent()) {
            modeloDeResposta.setMensagem("Dentista não encontrado!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.NOT_FOUND);
        }

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(consulta.getPaciente().getId());
        if (!optionalPaciente.isPresent()) {
            modeloDeResposta.setMensagem("Paciente não encontrado!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.NOT_FOUND);
        }

        consulta.setDentista(optionalDentista.get());
        consulta.setPaciente(optionalPaciente.get());
        consulta.setDataConsulta(consulta.getDataConsulta());
        System.out.println(consulta);
        Consulta consultaSalva = consultaRepository.save(consulta);
        return new ResponseEntity<Consulta>(consultaSalva, HttpStatus.CREATED);
    }

    public ResponseEntity<?> atualizar(Long id, Consulta consultaAtualizada) {
        // Verifica se a consulta a ser atualizada existe no banco de dados
        Consulta consultaEncontrada = consultaRepository.findById(id).orElse(null);
        if (consultaEncontrada == null) {
            modeloDeResposta.setMensagem("Consulta não encontrada!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }

        // Verifica se o id da consulta atualizada é o mesmo da consulta encontrada
        if (consultaAtualizada.getId() != consultaEncontrada.getId()) {
            modeloDeResposta.setMensagem("O id da consulta atualizada não corresponde ao id da consulta encontrada!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.BAD_REQUEST);
        }

        // Obtém as informações atualizadas da consulta
        Dentista dentistaAtualizado = consultaAtualizada.getDentista();
        Paciente pacienteAtualizado = consultaAtualizada.getPaciente();
        LocalDate dataConsultaAtualizada = consultaAtualizada.getDataConsulta();

        // Verifica se o dentista atualizado existe no banco de dados
        Optional<Dentista> optionalDentista = dentistaRepository.findById(dentistaAtualizado.getId());
        if (!optionalDentista.isPresent()) {
            modeloDeResposta.setMensagem("Dentista não encontrado!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.NOT_FOUND);
        }

        // Verifica se o paciente atualizado existe no banco de dados
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(pacienteAtualizado.getId());
        if (!optionalPaciente.isPresent()) {
            modeloDeResposta.setMensagem("Paciente não encontrado!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.NOT_FOUND);
        }

        // Atualiza a consulta com as informações atualizadas
        consultaEncontrada.setDentista(optionalDentista.get());
        consultaEncontrada.setPaciente(optionalPaciente.get());
        consultaEncontrada.setDataConsulta(dataConsultaAtualizada);

        // Salva a consulta atualizada no banco de dados
        Consulta consultaSalva = consultaRepository.save(consultaEncontrada);

        // Retorna uma mensagem de sucesso
        modeloDeResposta.setMensagem("Consulta atualizada com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }

    public ResponseEntity<ModeloDeResposta> remover(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if (!optionalConsulta.isPresent()) {
            modeloDeResposta.setMensagem("Consulta não encontrada!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }

        consultaRepository.deleteById(id);
        modeloDeResposta.setMensagem("Consulta excluída com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }

    public List<Dentista> findAllDentistas() {
        return consultaRepository.findAllDentistas();
    }

    public List<Paciente> findAllPacientes() {
        return consultaRepository.findAllPacientes();
    }
}