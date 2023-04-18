package br.com.projetointegrador.newdente.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DentistaDTO {

    private String nome;
    private String sobrenome;
    private String matricula;

}
