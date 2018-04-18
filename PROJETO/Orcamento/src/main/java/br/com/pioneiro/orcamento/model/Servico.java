package br.com.pioneiro.orcamento.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="servico")
public class Servico implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private BigDecimal valor;
	private String obs;

/*
---------------MÉTODOS GETTERS E SETTERS -------------------------
*/
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@NotBlank(message="O serviço deve ser informado.")
	@Column(name="nome", nullable=false, unique=true)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message="O valor do serviço deve ser informado.")
	@Column(name="valor", nullable=false, unique= false)
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(name="obs")
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
/*
---------------MÉTODOS EQUALS E HASHCODE -------------------------
*/	
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
		Servico other = (Servico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
