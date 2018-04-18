package br.com.pioneiro.orcamento.repository.helper.servico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pioneiro.orcamento.dto.ServicoDTO;
import br.com.pioneiro.orcamento.model.Servico;
import br.com.pioneiro.orcamento.repository.filter.ServicoFilter;

//INTERFACE COM MÉTODOS PERSONALISADOS PARA CONSULTAS NO BANCO.
public interface ServicosQueries 
{
	public Page<Servico> filtrar(ServicoFilter servicoFilter, Pageable pageable);
	
	//DTO = objeto de transferencia de dados, na hora consulta ele não vai trazer todos os atibutos de serviço
	public List<ServicoDTO> porNome(String nome);

}
