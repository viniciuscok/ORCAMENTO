package br.com.pioneiro.orcamento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pioneiro.orcamento.controller.page.PageWrapper;
import br.com.pioneiro.orcamento.model.Documento;
import br.com.pioneiro.orcamento.repository.Documentos;
import br.com.pioneiro.orcamento.repository.filter.DocumentoFilter;
import br.com.pioneiro.orcamento.service.CadastroDocumentoService;
import br.com.pioneiro.orcamento.service.exception.DocumentoJaCadastrado;

@Controller
@RequestMapping("/documentos")
public class DocumentoController
{
	@Autowired
	private CadastroDocumentoService cadastroDocumentoService;
	
	@Autowired
	private Documentos documentos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Documento documento)
	{
		ModelAndView mv = new ModelAndView("documento/CadastroDocumento");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Documento documento, BindingResult result, RedirectAttributes atributes)
	{
		if(result.hasErrors())
		{
			return novo(documento);
		}
		
		try
		{
			cadastroDocumentoService.salvar(documento);
		}catch(DocumentoJaCadastrado e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(documento);
		}
		atributes.addFlashAttribute("mensagem", "Docuemnto cadastrado com sucesso");
		return new ModelAndView("redirect:/documentos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(DocumentoFilter documentoFilter, BindingResult result, 
			@PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("documento/PesquisaDocumentos");
		
		PageWrapper<Documento> paginaWrapper = new PageWrapper<>(documentos.filtrar(documentoFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}

}
