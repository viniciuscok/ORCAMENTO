package br.com.pioneiro.orcamento.service.exception;

public class DocumentoJaCadastrado extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public DocumentoJaCadastrado(String message)
	{
		super(message);
	}

}
