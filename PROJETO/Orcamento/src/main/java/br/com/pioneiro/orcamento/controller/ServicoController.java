package br.com.pioneiro.orcamento.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pioneiro.orcamento.controller.page.PageWrapper;
import br.com.pioneiro.orcamento.dto.ServicoDTO;
import br.com.pioneiro.orcamento.model.Servico;
import br.com.pioneiro.orcamento.repository.Servicos;
import br.com.pioneiro.orcamento.repository.filter.ServicoFilter;
import br.com.pioneiro.orcamento.service.CadastroServicoService;
import br.com.pioneiro.orcamento.service.exception.ServicoCadastradoException;

@Controller
@RequestMapping("/servicos")
public class ServicoController
{
	
	@Autowired
	private CadastroServicoService cadastroServicoService;
	
	@Autowired
	private Servicos servicos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Servico servico)
	{
		ModelAndView mv = new ModelAndView("servico/CadastroServico");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Servico servico, BindingResult result, RedirectAttributes atributes)
	{
		//Se tiver error o método retorna o mesmo objeto de serviço.
		if(result.hasErrors())
		{
			return novo(servico);
		}
		
		try
		{
			cadastroServicoService.salvar(servico);
		}catch(ServicoCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(servico);
		}
		
		atributes.addFlashAttribute("mensagem", "Serviço cadastrado com sucesso");
		return new ModelAndView("redirect:/servicos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ServicoFilter servicoFilter, BindingResult result, 
			@PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("servico/PesquisaServicos");
		//System.out.println("numero da pagina= "+pageable.getPageNumber());
		//System.out.println("page size= "+ pageable.getPageSize());
		PageWrapper<Servico> paginaWrapper = new PageWrapper<>(servicos.filtrar(servicoFilter, pageable), 
				httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServicoDTO> pesquisar(String nome)
	{
		return servicos.porNome(nome);
	}

}
