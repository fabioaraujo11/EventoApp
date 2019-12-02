package br.com.resource.evento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.resource.evento.models.Convidado;
import br.com.resource.evento.models.Evento;
import br.com.resource.evento.repository.ConvidadoRepository;
import br.com.resource.evento.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;

	@GetMapping(value = "/cadastrarEvento")
	public String form() {
		return "evento/formEvento";
	}

	@PostMapping(value = "/cadastrarEvento")
	public String form(Evento evento) {
		String redr = "";
		try {
			er.save(evento);
			redr = "redirect:/cadastrarEvento";
		} catch (Exception e) {
			redr = "redirect:/error";
		}

		return redr;
	}

	@GetMapping(value = "/eventos")
	public ModelAndView listaEvento() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping(value = "/{idEvento}")
	public ModelAndView detalhesEvento(@PathVariable("idEvento") int idEvento) {
		Evento evento = er.findByidEvento(idEvento);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		return mv;
	}

	@PostMapping(value = "/{idEvento}")
	public String detalhesEventoPost(@PathVariable("idEvento") int idEvento, Convidado convidado) {

		Evento evento = er.findByidEvento(idEvento);
		convidado.setEvento(evento);
		cr.save(convidado);

		return "redirect:/{idEvento}";
	}

}
