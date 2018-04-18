package br.com.pioneiro.orcamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pioneiro.orcamento.model.Servico;
import br.com.pioneiro.orcamento.repository.filter.ServicoFilter;
import br.com.pioneiro.orcamento.repository.helper.servico.ServicosQueries;

@Repository
public interface Servicos extends JpaRepository<Servico, Long>, ServicosQueries
{
	public Optional<Servico> findByNomeIgnoreCase(String nome);
	public List<Servico> findByNome(ServicoFilter nome);
}
