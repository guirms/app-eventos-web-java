package com.guirms.eventoApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {
	
	@RequestMapping(value = "/cadastrarEvento")
	public String eventoForm() {
		return "evento/formEvento";
	}
	
	

}
