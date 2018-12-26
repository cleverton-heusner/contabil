package com.example.contabilidade.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.contabilidade.dto.EstatisticasLancamentosContabeis;
import com.example.contabilidade.model.LancamentoContabil;

@Repository
@Transactional
public class LancamentoContabilRepositorioImpl implements LancamentoContabilRepositorio {

	private final String FILTRO_POR_CONTA_CONTABIL = "LancamentoContabil.filtroPorContaContabil";
	private final String ESTATISTICAS = "LancamentosContabeis.estatisticas";

	private final String PARAM_CONTA_CONTABIL = "contaContabil";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<LancamentoContabil> filtrarPorContaContabil(final String contaContabil) {
		List<LancamentoContabil> lancamentosContabeis = entityManager
				.createNamedQuery(FILTRO_POR_CONTA_CONTABIL, LancamentoContabil.class)
				.setParameter(PARAM_CONTA_CONTABIL, contaContabil).getResultList();
		return lancamentosContabeis;
	}

	@Override
	public EstatisticasLancamentosContabeis exibirEstatisticas() {
		EstatisticasLancamentosContabeis estatisticas = entityManager
				.createNamedQuery(ESTATISTICAS, EstatisticasLancamentosContabeis.class).getSingleResult();
		return estatisticas;
	}
}
