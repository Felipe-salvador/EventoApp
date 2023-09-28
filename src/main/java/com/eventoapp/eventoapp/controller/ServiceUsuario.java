package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.Exceptions.ServiceExc;
import com.eventoapp.eventoapp.models.Usuario;
import com.eventoapp.eventoapp.repository.UsuarioRepository;

@Service
public class ServiceUsuario {
	
	@Autowired
	private UsuarioRepository ur;

	public Usuario loginUser(String user, String senha) throws ServiceExc{
		
		Usuario userLogin = ur.buscarLogin(user, senha);
		return userLogin;
}
}
