package br.com.pioneiro.orcamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController 
{
	@RequestMapping("/novo")
	public String novo()
	{
		return "pedido/CadastroPedido";
	}

}
