package com.example.contabilidade.unitario;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

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
public class BuscaLancamentoContabilPorIdTesteUnitario {

	@InjectMocks
	private LancamentoContabilService lancamentoContabilService;

	@Mock
	private LancamentoContabilRepositorioBase repositorio;

	@Captor
	private ArgumentCaptor<Long> idLancamentoParam;

	@Test
	public void deveriaBuscarPorId() {
		Long idLancamentoProcurado = 1l;
		LancamentoContabil lancamentoValido = LancamentoContabil.builder().contaContabil("1234567").data(new Date())
				.valor(new BigDecimal(10.00)).build();
		when(repositorio.findById(Mockito.anyLong())).thenReturn(Optional.of(lancamentoValido));

		lancamentoContabilService.buscarPorId(idLancamentoProcurado);

		verify(repositorio).findById(idLancamentoParam.capture());
		assertThat(idLancamentoProcurado, is(equalTo(idLancamentoParam.getValue())));
	}
}
