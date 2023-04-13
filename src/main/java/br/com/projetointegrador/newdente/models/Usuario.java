package br.com.projetointegrador.newdente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;

    @Column(unique = true) // adiciona a validação de login único
    @NotBlank(message = "O campo login é obrigatório")
    private String login;
    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;
    @NotBlank(message = "O campo função é obrigatório")
    private String funcao;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
