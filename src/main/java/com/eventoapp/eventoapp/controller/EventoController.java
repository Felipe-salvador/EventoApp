package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastraEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastraEvento", method=RequestMethod.POST)
	public String form(Evento evento) {	
		if(evento.getNome().isEmpty()
				|| evento.getLocal().isEmpty()
				|| evento.getData().isEmpty()
				|| evento.getHorario().isEmpty()) {
			
		}else {
			er.save(evento);
		}
		
		return "redirect:/cadastraEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEventos(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		return mv;
	}
	

	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventosPost(@PathVariable("codigo") long codigo, Convidado convidado){
		Evento evento = er.findByCodigo(codigo);
		if(evento.getNome().isEmpty()
				|| evento.getLocal().isEmpty()
				|| evento.getData().isEmpty()
				|| evento.getHorario().isEmpty()) {	
		}else {
		convidado.setEvento(evento);
		cr.save(convidado);
		}
		return "redirect:/{codigo}";
	}	
}
