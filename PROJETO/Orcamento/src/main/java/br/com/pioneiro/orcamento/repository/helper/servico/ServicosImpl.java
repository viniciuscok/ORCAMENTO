package br.com.pioneiro.orcamento.repository.helper.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.util.StringUtils;

import br.com.pioneiro.orcamento.dto.ServicoDTO;
import br.com.pioneiro.orcamento.model.Servico;
import br.com.pioneiro.orcamento.repository.filter.ServicoFilter;

public class ServicosImpl implements ServicosQueries
{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	public Page<Servico> filtrar(ServicoFilter servicoFilter, Pageable pageable) 
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Servico.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(servicoFilter, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(servicoFilter));
	}

	private void adicionarFiltro(ServicoFilter servicoFilter, Criteria criteria) {
		if(servicoFilter != null)
		{
			if(!StringUtils.isEmpty(servicoFilter.getNome()))
			{
				criteria.add(Restrictions.ilike("nome", servicoFilter.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

	private long total(ServicoFilter servicoFilter) 
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Servico.class);
		adicionarFiltro(servicoFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public List<ServicoDTO> porNome(String nome) 
	{
		String jpql = "select new br.com.pioneiro.orcamento.dto.ServicoDTO(codigo, nome, valor) "
				+ "from Servico where nome like :nome";
		List<ServicoDTO> servicosFiltrados = manager.createQuery(jpql, ServicoDTO.class).setParameter("nome", nome+"%").getResultList();
		return servicosFiltrados;
	}

}
