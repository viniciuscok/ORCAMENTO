package br.com.pioneiro.orcamento.repository.helper.servico;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil 
{
	public void preparar(Criteria criteria, Pageable pageable)
	{
		int paginaAtual = pageable.getPageNumber();
		int quantidadeTotalPaginas = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * quantidadeTotalPaginas;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(quantidadeTotalPaginas);
		
		Sort sort = pageable.getSort();
		if(sort != null && sort.isSorted())
		{
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
	}

}
