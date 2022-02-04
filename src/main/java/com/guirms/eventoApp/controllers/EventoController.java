package com.guirms.eventoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guirms.eventoApp.models.Evento;
import com.guirms.eventoApp.repositories.eventoRepository;

@Controller
public class EventoController {
	
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
	
	
	
	

}
