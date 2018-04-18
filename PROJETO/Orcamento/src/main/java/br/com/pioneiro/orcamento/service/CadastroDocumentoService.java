package br.com.pioneiro.orcamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pioneiro.orcamento.model.Documento;
import br.com.pioneiro.orcamento.repository.Documentos;
import br.com.pioneiro.orcamento.service.exception.DocumentoJaCadastrado;

@Service
public class CadastroDocumentoService 
{
	@Autowired
	private Documentos documentos;
	
	@Transactional
	public void salvar(Documento documento)
	{
		Optional<Documento> documentoOptional = documentos.findByNomeIgnoreCase(documento.getNome());
		if(documentoOptional.isPresent())
		{
			throw new DocumentoJaCadastrado("Nome do documento já cadastrado.");
		}
		
		documentos.save(documento);
	}

}
