package com.example.contabilidade.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Getter
public class EstatisticasLancamentosContabeis {

	private Long numeroLancamentos;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private BigDecimal somaValores;
	private Double valorMedio;
}
