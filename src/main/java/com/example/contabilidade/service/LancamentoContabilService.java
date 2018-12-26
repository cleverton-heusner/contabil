package com.example.contabilidade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contabilidade.dto.EstatisticasLancamentosContabeis;
import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;

@Service
public class LancamentoContabilService {

	@Autowired
	private LancamentoContabilRepositorioBase repositorio;
	
	public List<LancamentoContabil> listar() {
		return repositorio.findAll();
	}

	public Optional<LancamentoContabil> buscarPorId(final Long id) {
		Optional<LancamentoContabil> lancamentoBuscado = repositorio.findById(id);
		return lancamentoBuscado;
	}

	public List<LancamentoContabil> filtrarPorContaContabil(final String contaContabil) {
		return repositorio.filtrarPorContaContabil(contaContabil);
	}

	public EstatisticasLancamentosContabeis exibirEstatisticas() {
		return repositorio.exibirEstatisticas();
	}

	public LancamentoContabil cadastrar(final LancamentoContabil lancamentoContabil) {
		LancamentoContabil lancamentoCriado = repositorio.save(lancamentoContabil);
		return lancamentoCriado;
	}
}
