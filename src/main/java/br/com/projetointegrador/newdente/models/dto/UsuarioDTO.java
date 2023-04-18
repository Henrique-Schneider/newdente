package br.com.projetointegrador.newdente.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String usuario;
    private String login;
    private String senha;
    private String funcao;

}
