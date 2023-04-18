package br.com.projetointegrador.newdente.models.dto;

import br.com.projetointegrador.newdente.models.Consulta;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {
    private Long dentistaId;
    private Long pacienteId;
    private LocalDate dataConsulta;

    public Consulta toConsulta() {
        Consulta consulta = new Consulta();
        consulta.setDentistaId(this.getDentistaId());
        consulta.setPacienteId(this.getPacienteId());
        consulta.setDataConsulta(this.getDataConsulta());
        // Outros campos, se houver
        return consulta;
    }
}
