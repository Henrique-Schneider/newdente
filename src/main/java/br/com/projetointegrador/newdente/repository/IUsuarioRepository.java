package br.com.projetointegrador.newdente.repository;


import br.com.projetointegrador.newdente.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {
    UserDetails findByLogin(String login);

   }
