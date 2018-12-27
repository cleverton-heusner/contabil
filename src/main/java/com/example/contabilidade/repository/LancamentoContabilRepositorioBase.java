package com.example.contabilidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contabilidade.model.LancamentoContabil;

@Repository
public interface LancamentoContabilRepositorioBase
		extends JpaRepository<LancamentoContabil, Long>, LancamentoContabilRepositorio {
}
