package br.com.pioneiro.orcamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pioneiro.orcamento.model.Servico;
import br.com.pioneiro.orcamento.repository.Servicos;
import br.com.pioneiro.orcamento.service.exception.ServicoCadastradoException;

@Service
public class CadastroServicoService 
{
	@Autowired
	private Servicos servicos;
	
	@Transactional
	public void salvar(Servico servico)
	{
		//verifica se existe serviços duplicados.
		Optional<Servico> servicoOptional = servicos.findByNomeIgnoreCase(servico.getNome());
		if(servicoOptional.isPresent())
		{
			throw new ServicoCadastradoException("Nome do serviço já cadastrado.");
		}
		servicos.save(servico);
	}

}
