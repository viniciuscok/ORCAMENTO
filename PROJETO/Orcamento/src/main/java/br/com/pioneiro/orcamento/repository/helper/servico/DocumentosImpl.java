package br.com.pioneiro.orcamento.repository.helper.servico;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.pioneiro.orcamento.model.Documento;
import br.com.pioneiro.orcamento.repository.filter.DocumentoFilter;


public class DocumentosImpl implements DocumentosQueries
{
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public Page<Documento> filtrar(DocumentoFilter documentoFilter, Pageable pageable) 
	{
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Documento.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(documentoFilter, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(documentoFilter));
	}

	private void adicionarFiltro(DocumentoFilter documentoFilter, Criteria criteria) {
		if(documentoFilter != null)
		{
			if(!StringUtils.isEmpty(documentoFilter.getNome()))
			{
				criteria.add(Restrictions.ilike("nome", documentoFilter.getNome(), MatchMode.ANYWHERE));
			}
		}
	}
	
	public Long total(DocumentoFilter documentoFilter)
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Documento.class);
		adicionarFiltro(documentoFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
	
	

}
