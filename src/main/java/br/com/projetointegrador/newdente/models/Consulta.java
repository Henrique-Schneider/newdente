package br.com.projetointegrador.newdente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "consulta")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_consulta")
    private LocalDate dataConsulta;

    @Transient
    private Long dentistaId;

    @Transient
    private Long pacienteId;

    // getters e setters para os atributos transient

    public void setDentistaId(Long dentistaId) {
        this.dentistaId = dentistaId;
        this.dentista = new Dentista();
        this.dentista.setId(dentistaId);
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
        this.paciente = new Paciente();
        this.paciente.setId(pacienteId);
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", dentista=" + dentista +
                ", paciente=" + paciente +
                ", dataConsulta=" + dataConsulta +
                ", dentistaId=" + dentistaId +
                ", pacienteId=" + pacienteId +
                '}';
    }
}
