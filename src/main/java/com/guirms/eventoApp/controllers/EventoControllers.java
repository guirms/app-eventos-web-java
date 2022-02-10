package com.guirms.eventoApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.guirms.eventoApp.models.Evento;
import com.guirms.eventoApp.repositories.eventoRepository;

@Controller
public class EventoControllers {
	
	@Autowired
	eventoRepository eventoRepo;	
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String eventoForm() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String eventoForm(Evento evento) {
		eventoRepo.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping(value = "/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepo.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") Long id) {
		Optional<Evento> evento = eventoRepo.findById(id);
		ModelAndView mv = new ModelAndView("DetalhesEvento");
		mv.addObject("evento", evento);
		return mv;
	}
	
	
	

}