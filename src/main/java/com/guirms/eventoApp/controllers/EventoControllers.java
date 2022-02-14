package com.guirms.eventoApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String eventoForm(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/cadastrarEvento";
		} else {
			eventoRepo.save(evento);
			attributes.addFlashAttribute("flashMessage", "Evento adicionado com sucesso!");
			attributes.addFlashAttribute("flashType", "success");
			return "redirect:/cadastrarEvento";
		}
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
	public String detalhesEventoPost(@PathVariable("codigo") Long codigo, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/{codigo}";
		} else {
			Evento evento = eventoRepo.findByCodigo(codigo);
			convidado.setEvento(evento);
			convidadoRepo.save(convidado);
			attributes.addFlashAttribute("flashMessage", "Convidado adicionado com sucesso!");
			attributes.addFlashAttribute("flashType", "success");
			return "redirect:/{codigo}";
		}
	}
	
	@RequestMapping("/deletarEvento")
	public String deletarEvento(Long codigo) {
		Evento evento = eventoRepo.findByCodigo(codigo);
		eventoRepo.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(Integer rg) {
		Convidado convidado = convidadoRepo.findByRg(rg);
		convidadoRepo.delete(convidado);
		Evento evento = convidado.getEvento();
		Long codigoLong = evento.getCodigo();
		String codigoString = "" + codigoLong;
		return "redirect:/" + codigoString;
	}
}
