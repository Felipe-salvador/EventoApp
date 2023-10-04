package com.eventoapp.eventoapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.eventoapp.eventoapp.models.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query("select i from Usuario i where i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select j from Usuario j where j.user = :user and j.senha = :senha")
	public Usuario buscarLogin(String user, String senha);
} 