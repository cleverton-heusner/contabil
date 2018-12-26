package com.example.contabilidade.unitario;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;
import com.example.contabilidade.service.LancamentoContabilService;

@RunWith(MockitoJUnitRunner.class)
public class BuscaLancamentoContabilPorContaTesteUnitario {

	@InjectMocks
	private LancamentoContabilService lancamentoContabilService;

	@Mock
	private LancamentoContabilRepositorioBase repositorio;

	@Captor
	private ArgumentCaptor<String> contaContabilParam;

	@Test
	public void deveriaBuscarPorConta() {
		String contaContabilProcurada = "1234567";
		LancamentoContabil lancamento = LancamentoContabil.builder().contaContabil("1234567").data(new Date())
				.valor(new BigDecimal(10.00)).build();
		List<LancamentoContabil> lancamentos = Arrays.asList(lancamento);
		when(repositorio.filtrarPorContaContabil(Mockito.anyString())).thenReturn(lancamentos);

		lancamentoContabilService.filtrarPorContaContabil(contaContabilProcurada);

		verify(repositorio).filtrarPorContaContabil(contaContabilParam.capture());
		assertThat(contaContabilProcurada, is(equalTo(contaContabilParam.getValue())));
	}
}
