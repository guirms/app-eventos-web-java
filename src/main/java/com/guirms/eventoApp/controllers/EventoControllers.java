package com.guirms.eventoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.guirms.eventoApp.models.Convidado;
import com.guirms.eventoApp.models.Evento;
import com.guirms.eventoApp.repositories.convidadoRepository;
import com.guirms.eventoApp.repositories.eventoRepository;

@Controller
public class EventoControllers {
	
	@Autowired
	eventoRepository eventoRepo;	
	
	@Autowired
	convidadoRepository convidadoRepo;	
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String eventoForm() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String eventoForm(Evento evento) {
		eventoRepo.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepo.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
		Evento eventos = eventoRepo.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/eventoDetalhes");
		mv.addObject("eventos", eventos); // o que se usa como referencia é esse segundo "eventos"
		Iterable<Convidado> convidados = convidadoRepo.findByEvento(eventos);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") Long codigo, Convidado convidado) {
		Evento evento = eventoRepo.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepo.save(convidado);
		return "redirect:/{codigo}";
	}

}