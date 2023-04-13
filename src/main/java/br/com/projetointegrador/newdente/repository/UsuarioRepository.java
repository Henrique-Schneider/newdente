package br.com.projetointegrador.newdente.repository;


import br.com.projetointegrador.newdente.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Usuario findByLogin(String login);

   }
