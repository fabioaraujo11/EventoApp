package br.com.resource.evento.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos corretamente");
			return "redirect:/cadastrarEvento";
		}

		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/cadastrarEvento";
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

		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);

		return mv;
	}

	@PostMapping(value = "/{idEvento}")
	public String detalhesEventoPost(@PathVariable("idEvento") int idEvento, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos corretamente");
			return "redirect:/{idEvento}";
		}

		Evento evento = er.findByidEvento(idEvento);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/{idEvento}";
	}

	@GetMapping(value = "/deletarEvento")
	public String deletarEvento(int idEvento) {
		Evento evento = er.findByidEvento(idEvento);

		er.delete(evento);

		return "redirect:/eventos";
	}

	@GetMapping(value = "/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);

		Evento evento = convidado.getEvento();
		int idEventoINT = evento.getIdEvento();
		String codigo = "" + idEventoINT;

		return "redirect:/" + codigo;
	}

}
