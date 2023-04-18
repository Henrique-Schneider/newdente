package br.com.projetointegrador.newdente.models.validacao;

import org.springframework.stereotype.Component;

@Component
public class ModeloDeResposta {
    
    private String mensagem;



    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}