package br.com.pioneiro.orcamento.dto;

import java.math.BigDecimal;

public class ServicoDTO 
{
	private Long codigo;
	private String nome;
	private BigDecimal valor;
	
	public ServicoDTO(Long codigo, String nome, BigDecimal valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
