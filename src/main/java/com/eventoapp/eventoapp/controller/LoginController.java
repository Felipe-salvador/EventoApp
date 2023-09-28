package com.eventoapp.eventoapp.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.Exceptions.ServiceExc;
import com.eventoapp.eventoapp.models.Usuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private ServiceUsuario su;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login/login");
		return mv;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@PostMapping("/logar")
	public ModelAndView logar(@Valid Usuario usuario,  BindingResult result, HttpSession session, RedirectAttributes attributes) throws NoSuchAlgorithmException, ServiceExc {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if(result.hasErrors()) {
			mv.setViewName("redirect:/");
		}	
		
		Usuario userLogin = su.loginUser(usuario.getUser(), usuario.getSenha());
		if(userLogin == null) {
			attributes.addFlashAttribute("mensagem", "Usuario não encontrado. tente novamente");
			mv.setViewName("redirect:/");
		}else {
			session.setAttribute("usuarioLogado", userLogin);
			return index();
		}
		
		return mv;
	}
}
