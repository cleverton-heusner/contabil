package com.example.contabilidade.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.contabilidade.dto.EstatisticasLancamentosContabeis;
import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.service.LancamentoContabilService;

@RestController
@RequestMapping("/lancamentos-contabeis")
public class LancamentoContabilResource {

	@Autowired
	private LancamentoContabilService lancamentoContabilService;

	@GetMapping
	public List<LancamentoContabil> listar() {
		return lancamentoContabilService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LancamentoContabil> buscarPorId(@PathVariable Long id) {
		Optional<LancamentoContabil> lancamentoBuscado = lancamentoContabilService.buscarPorId(id);
		return ResponseEntity.of(lancamentoBuscado);
	}

	@GetMapping("/por-conta-contabil")
	public List<LancamentoContabil> filtrarPorContaContabil(@RequestParam String contaContabil) {
		return lancamentoContabilService.filtrarPorContaContabil(contaContabil);
	}

	@GetMapping("/estatisticas")
	public EstatisticasLancamentosContabeis exibirEstatisticas() {
		return lancamentoContabilService.exibirEstatisticas();
	}

	@PostMapping
	public ResponseEntity<LancamentoContabil> cadastrar(@RequestBody @Valid LancamentoContabil lancamentoContabil) {
		LancamentoContabil lancamentoCriado = lancamentoContabilService.cadastrar(lancamentoContabil);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCriado);
	}
}
