package br.com.projetointegrador.newdente.models.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private String nome;
    private String sobrenome;
    private String rg;
    private LocalDate dataAlta;
    private String endereco;
}
