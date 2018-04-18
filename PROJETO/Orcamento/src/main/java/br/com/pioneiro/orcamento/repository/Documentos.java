package br.com.pioneiro.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pioneiro.orcamento.model.Documento;
import br.com.pioneiro.orcamento.repository.helper.servico.DocumentosQueries;

public interface Documentos extends JpaRepository<Documento, Long>, DocumentosQueries
{
	public Optional<Documento> findByNomeIgnoreCase(String nome);
}
