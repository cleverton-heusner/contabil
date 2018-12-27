package com.example.contabilidade.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.example.contabilidade.validacao.Valor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lancamento_contabil")
@ToString
@Builder
@EqualsAndHashCode
@Getter
@NamedQueries({
		@NamedQuery(name = "LancamentoContabil.filtroPorContaContabil", query = "SELECT"
				+ " l FROM LancamentoContabil l WHERE l.contaContabil = :contaContabil"),
		@NamedQuery(name = "LancamentosContabeis.estatisticas", query = "SELECT new "
				+ "com.example.contabilidade.dto.EstatisticasLancamentosContabeis(count(l.id) AS numLancamentos,"
				+ "round(coalesce(min(l.valor),0),2) AS valorMinimo, "
				+ "round(coalesce(max(l.valor),0),2) AS valorMaximo, "
				+ "round(coalesce(sum(l.valor),0),2) AS somaValores, "
				+ "round(coalesce(avg(l.valor),0),2) AS valorMedio) FROM LancamentoContabil l") })
public class LancamentoContabil {

	public LancamentoContabil() {
	}

	public LancamentoContabil(Long id, String contaContabil, Date data, BigDecimal valor) {
		this.id = id;
		this.contaContabil = contaContabil;
		this.data = data;
		this.valor = valor;
	}

	@Transient
	@Getter(onMethod = @__( @JsonIgnore ))
	private final String PADRAO_CONTA_CONTABIL = "[0-9]{7}";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Pattern(regexp = PADRAO_CONTA_CONTABIL, message = "{conta_contabil.invalida}")
	@NotNull(message = "{conta_contabil.notnull}")
	private String contaContabil;

	@Temporal(TemporalType.DATE)
	@Setter
	@PastOrPresent(message = "{data.maior_que_atual}")
	@NotNull(message = "{data.notnull}")
	private Date data;

	@Setter
	@Valor(message = "{valor.invalido}")
	private BigDecimal valor;
}