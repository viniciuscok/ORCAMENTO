package br.com.pioneiro.orcamento.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
//@Table(name="itempedido")
public class ItemPedido implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private Integer quantidade = 1;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal acrescimo = BigDecimal.ZERO;
	private Servico servico;
	private Pedido pedido;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getAcrescimo() {
		return acrescimo;
	}
	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	} 
	
	@Transient
	public BigDecimal getValorTotal()
	{
		return this.valorUnitario.multiply(new BigDecimal(this.quantidade).add(this.getAcrescimo()));
	}

}
