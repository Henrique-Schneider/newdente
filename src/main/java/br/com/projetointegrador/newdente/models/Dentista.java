package br.com.projetointegrador.newdente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dentista")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;
    @Column(name = "matricula", nullable = false)
    private String matricula;

    public Dentista(Long dentistaId) {
        this.id = dentistaId;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
