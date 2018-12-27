package com.example.contabilidade.unitario;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;
import com.example.contabilidade.service.LancamentoContabilService;

@RunWith(MockitoJUnitRunner.class)
public class CadastroLancamentoContabilTesteUnitario {

	@InjectMocks
	private LancamentoContabilService lancamentoContabilService;

	@Mock
	private LancamentoContabilRepositorioBase repositorio;

	@Captor
	private ArgumentCaptor<LancamentoContabil> lancamentoParam;

	@Test
	public void deveriaCadastrar() {
		LancamentoContabil lancamento = LancamentoContabil.builder().contaContabil("1234567").data(new Date())
				.valor(new BigDecimal(10.00)).build();
		when(repositorio.save(lancamento)).thenReturn(lancamento);

		lancamentoContabilService.cadastrar(lancamento);

		verify(repositorio).save(lancamentoParam.capture());
		assertThat(lancamento.getContaContabil(), is(equalTo(lancamentoParam.getValue().getContaContabil())));
		assertThat(lancamento.getData(), is(equalTo(lancamentoParam.getValue().getData())));
		assertThat(lancamento.getValor(), is(equalTo(lancamentoParam.getValue().getValor())));
	}
}
