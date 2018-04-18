package br.com.pioneiro.orcamento.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

//classe para 
public class PageWrapper<T> 
{
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) 
	{
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}
	
	//Método para retornar o conteúdo da página
	public List<T> getConteudo()
	{
		return page.getContent();
	}
	
	//Método que verifica se existe alguma coisa na página.
	public boolean isVazia()
	{
		return page.getContent().isEmpty();
	}
	
	//método que retorna o número da página atual.
	public int getPaginaAtual()
	{
		return page.getNumber();
	}
	
	//Método para verificar se é a primeira página.
	public boolean isPrimeiraPagina()
	{
		return page.isFirst();
	}
	
	//Método para verificar se é a ultima Página.
	public boolean isUltimaPagina()
	{
		return page.isLast();
	}
	
	//Método que retorna o total de páginas.
	public int getTotalPagina()
	{
		return page.getTotalPages();
	}
	
	//Método para mudança de página com o filtro permanente.
	public String urlParaPagina(int pagina)
	{
		
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	//Método para mudança de página com o sort buscando pelo field da tabela
	public String urlOrdenada(String propriedade)
	{
		//criando uma nova builder para chamar novas paginas na url.
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toString();
	}
	
	public String inverterDirecao(String propriedade)
	{
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if(order != null)
		{
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		return direcao;
	}
	
	
	public boolean descendente(String propriedade)
	{
		return inverterDirecao(propriedade).equals("asc");
	}

	public boolean ordenada(String propriedade)
	{
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade):null;
		if(order ==null)
		{
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
}
