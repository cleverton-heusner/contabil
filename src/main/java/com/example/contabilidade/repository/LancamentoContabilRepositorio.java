package com.example.contabilidade.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.contabilidade.dto.EstatisticasLancamentosContabeis;
import com.example.contabilidade.model.LancamentoContabil;

@Repository
public interface LancamentoContabilRepositorio {
	public List<LancamentoContabil> filtrarPorContaContabil(final String contaContabil);

	public EstatisticasLancamentosContabeis exibirEstatisticas();
}
