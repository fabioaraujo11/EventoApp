package br.com.resource.evento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.resource.evento.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {


	Usuario findByusername(String username);
}
