package br.com.pioneiro.orcamento.model;

public enum StatusPedido 
{
	ORCAMENTO("Orçamento"), 
	CANCELADO("Cancelado"),
	EMITIDO("Emitido");
	
	private String descricao;
	
	private StatusPedido(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
