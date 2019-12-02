package br.com.resource.evento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/errorErrou")
	public String error() {
		return "error";
	}
	
}
