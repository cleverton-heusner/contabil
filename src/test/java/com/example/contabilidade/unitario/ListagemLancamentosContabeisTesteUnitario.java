package com.example.contabilidade.unitario;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;
import com.example.contabilidade.service.LancamentoContabilService;

@RunWith(MockitoJUnitRunner.class)
public class ListagemLancamentosContabeisTesteUnitario {

	@InjectMocks
	private LancamentoContabilService lancamentoContabilService;

	@Mock
	private LancamentoContabilRepositorioBase repositorio;

	@Test
	public void deveriaListar() {
		LancamentoContabil lancamento = LancamentoContabil.builder().contaContabil("1234567").data(new Date())
				.valor(new BigDecimal(10.00)).build();
		List<LancamentoContabil> lancamentos = Arrays.asList(lancamento);
		when(repositorio.findAll()).thenReturn(lancamentos);

		lancamentoContabilService.listar();

		verify(repositorio).findAll();
	}
}
