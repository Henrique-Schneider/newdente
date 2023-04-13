package br.com.projetointegrador.newdente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "rg", nullable = false, unique = true)
    private String rg;

    @Column(name = "data_alta", nullable = false)
    private LocalDate dataAlta;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    public Paciente(Long pacietneId) {
        this.id = pacietneId;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", rg='" + rg + '\'' +
                ", dataAlta=" + dataAlta +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}


