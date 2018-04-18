package br.com.pioneiro.orcamento.repository.helper.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pioneiro.orcamento.model.Documento;
import br.com.pioneiro.orcamento.repository.filter.DocumentoFilter;

//INTERFACE COM MÉTODOS PERSONALISADOS PARA CONSULTAS NO BANCO.
public interface DocumentosQueries 
{
	public Page<Documento> filtrar(DocumentoFilter documentoFilter, Pageable pageable);

}
