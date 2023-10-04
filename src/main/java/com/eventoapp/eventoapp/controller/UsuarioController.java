package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.eventoapp.ServiceUsuario;
import com.eventoapp.eventoapp.models.Usuario;
import com.eventoapp.eventoapp.repository.UsuarioRepository;


import jakarta.validation.Valid;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private ServiceUsuario su;

	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String cadastrar() {
		return "Login/cadastro";
	}
	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String cadastra(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) throws Exception {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarUsuario";
			
		}else if (ur.findByEmail(usuario.getEmail()) != null) {
			attributes.addFlashAttribute("mensagem", "já existe um email cadastrado para:" + usuario.getEmail());
			return "redirect:/cadastrarUsuario";
		}else {
			su.salvarUsuario(usuario);
			attributes.addFlashAttribute("mensagemOK", "Cadastro realizado com sucesso!");
			return "Login/login";
		}
		
	}
	


}
