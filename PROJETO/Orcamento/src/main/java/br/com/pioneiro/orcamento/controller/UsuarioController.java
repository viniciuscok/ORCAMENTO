package br.com.pioneiro.orcamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController 
{
	@GetMapping("home")
	public String teste()
	{
		return "home";
	}

}
