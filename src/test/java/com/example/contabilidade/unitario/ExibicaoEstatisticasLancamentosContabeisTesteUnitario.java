package com.example.contabilidade.unitario;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.contabilidade.dto.EstatisticasLancamentosContabeis;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;
import com.example.contabilidade.service.LancamentoContabilService;

@RunWith(MockitoJUnitRunner.class)
public class ExibicaoEstatisticasLancamentosContabeisTesteUnitario {

	@InjectMocks
	private LancamentoContabilService lancamentoContabilService;

	@Mock
	private LancamentoContabilRepositorioBase repositorio;

	@Test
	public void deveriaExibirEstatisticas() {
		EstatisticasLancamentosContabeis estatisticas = EstatisticasLancamentosContabeis.builder().numeroLancamentos(1l)
				.somaValores(new BigDecimal(3)).valorMinimo(new BigDecimal(1)).valorMaximo(new BigDecimal(2))
				.valorMedio(1.5d).build();
		when(repositorio.exibirEstatisticas()).thenReturn(estatisticas);

		lancamentoContabilService.exibirEstatisticas();

		verify(repositorio).exibirEstatisticas();
	}
}
