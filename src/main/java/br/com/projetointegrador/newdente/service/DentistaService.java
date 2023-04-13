package br.com.projetointegrador.newdente.service;


import br.com.projetointegrador.newdente.models.Dentista;
import br.com.projetointegrador.newdente.models.Usuario;
import br.com.projetointegrador.newdente.models.validacao.ModeloDeResposta;
import br.com.projetointegrador.newdente.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private ModeloDeResposta modeloDeResposta;

    // metodo para listar todos os dentistas;
    public Iterable<Dentista> listar() {
        return dentistaRepository.findAll();
    }

    // metodo para buscar por ID
    public Dentista buscarDentistaPorId(Long id) {
        return dentistaRepository.findById(id).orElse(null);
    }

    // Metodo para cadastrar dentista
    public ResponseEntity<?> cadastrar(Dentista dentista) {
        if (dentista.getMatricula().equals("")) {
            modeloDeResposta.setMensagem("A matrícula é obrigatória!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else if (dentista.getNome().equals("")) {
            modeloDeResposta.setMensagem("O nome é obrigatório!");
            return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
        } else {
            // Verifica se a matrícula já existe no banco
            Dentista dentistaExistente = dentistaRepository.findByMatricula(dentista.getMatricula());
            if (dentistaExistente != null) {
                modeloDeResposta.setMensagem("A matrícula já existe!");
                return new ResponseEntity<String>(modeloDeResposta.getMensagem(), HttpStatus.BAD_REQUEST);
            }

            // Salva o dentista no banco
            Dentista dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity<Dentista>(dentistaSalvo, HttpStatus.CREATED);
        }
    }

    //  Metodo para  editar dentista
    public ResponseEntity<?> atualizar(Long id, Dentista dentistaAtualizado) {
        Optional<Dentista> optionalDentista = dentistaRepository.findById(id);
        if (!optionalDentista.isPresent()) {
            modeloDeResposta.setMensagem("Dentista não encontrado!");
            return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.NOT_FOUND);
        }
        Dentista dentistaEncontrado = optionalDentista.get();
        dentistaEncontrado.setNome(dentistaAtualizado.getNome());
        dentistaEncontrado.setSobrenome(dentistaAtualizado.getSobrenome());
        dentistaEncontrado.setMatricula(dentistaAtualizado.getMatricula());

        Dentista dentistaSalvo = dentistaRepository.save(dentistaEncontrado);
        modeloDeResposta.setMensagem("Dentista atualizado com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta, HttpStatus.OK);
    }


    //    Metodo para excluir por ID
    public ResponseEntity<ModeloDeResposta> remover(long id){
        dentistaRepository.deleteById(id);
        modeloDeResposta.setMensagem("dentista removido com sucesso!");
        return new ResponseEntity<ModeloDeResposta>(modeloDeResposta,HttpStatus.OK);
    }

}