package com.eventoapp.eventoapp;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.Exceptions.CriptoExistException;
import com.eventoapp.Exceptions.EmailExistsException;
import com.eventoapp.Exceptions.ServiceExc;
import com.eventoapp.eventoapp.models.Usuario;
import com.eventoapp.eventoapp.repository.UsuarioRepository;
import com.eventoapp.util.PasswordUtil;

@Service
public class ServiceUsuario {
	
	@Autowired
	private UsuarioRepository ur;
	
	public void salvarUsuario(Usuario user) throws Exception{
		
		try {
	
			if(ur.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("já existe um email cadastrado para: " + user.getEmail());

			}
			user.setSenha(PasswordUtil.md5(user.getSenha()));
			
		}catch (NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		
		ur.save(user);
	}

	public Usuario loginUser(String user, String senha) throws ServiceExc{
		
		Usuario userLogin = ur.buscarLogin(user, senha);
		return userLogin;
    }
}
