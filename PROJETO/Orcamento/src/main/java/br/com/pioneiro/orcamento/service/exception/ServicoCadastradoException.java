package br.com.pioneiro.orcamento.service.exception;

public class ServicoCadastradoException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public ServicoCadastradoException(String message)
	{
		super(message);
	}

}
