package com.example.contabilidade.integracao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.contabilidade.model.LancamentoContabil;
import com.example.contabilidade.repository.LancamentoContabilRepositorioBase;
import com.example.contabilidade.utils.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("rawtypes")
public class CadastroLancamentoContabilTesteIntegracao {

	private final String URL_LANCAMENTOS_CONTABEIS = "/lancamentos-contabeis";
	private final String CONTA_CONTABIL_INVALIDA = "1";

	@Autowired
	private Data data;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LancamentoContabilRepositorioBase repositorio;
	private LancamentoContabil lancamentoEsperadoPadrao;

	@Before
	public void setUp() {
		lancamentoEsperadoPadrao = LancamentoContabil.builder().contaContabil("1234567").data(new Date())
				.valor(new BigDecimal(10.00)).build();
	}

	@Test
	public void deveriaCadastrarQuandoTodosOsDadosForemValidos() {
		ResponseEntity<LancamentoContabil> resposta = restTemplate.postForEntity(URL_LANCAMENTOS_CONTABEIS,
				lancamentoEsperadoPadrao, LancamentoContabil.class);
		LancamentoContabil lancamentoObtido = resposta.getBody();

		assertThat(HttpStatus.CREATED, is(equalTo(resposta.getStatusCode())));
		assertThat(lancamentoEsperadoPadrao.getContaContabil(), is(equalTo(lancamentoObtido.getContaContabil())));
		assertThat(lancamentoEsperadoPadrao.getData(), is(equalTo(lancamentoObtido.getData())));
		assertThat(lancamentoEsperadoPadrao.getValor(), is(equalTo(lancamentoObtido.getValor())));

		repositorio.deleteById(lancamentoObtido.getId());
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComContaInvalida() {
		lancamentoEsperadoPadrao.setContaContabil(CONTA_CONTABIL_INVALIDA);
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("A conta contábil deve ter 7 dígitos.", is(equalTo(obterMensagemDe(resposta))));
	}

	private ResponseEntity<List> requisitarCadastroLancamentoInvalido() {
		return restTemplate.postForEntity(URL_LANCAMENTOS_CONTABEIS, lancamentoEsperadoPadrao, List.class);
	}

	private String obterMensagemDe(ResponseEntity<List> resposta) {
		return (String) resposta.getBody().get(0);
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComContaNula() {
		lancamentoEsperadoPadrao.setContaContabil(null);
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("Conta contábil não informada.", is(equalTo(obterMensagemDe(resposta))));
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComDataDeAmanha() {
		lancamentoEsperadoPadrao.setData(data.amanha());
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("A data informada não deve exceder a atual.", is(equalTo(obterMensagemDe(resposta))));
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComDataNula() {
		lancamentoEsperadoPadrao.setData(null);
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("Data não informada.", is(equalTo(obterMensagemDe(resposta))));
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComValorZerado() {
		lancamentoEsperadoPadrao.setValor(new BigDecimal(0.00));
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("O valor não pode ser nulo ou zerado.", is(equalTo(obterMensagemDe(resposta))));
	}

	@Test
	public void deveriaFalharAoTentarCadastrarComValorNulo() {
		lancamentoEsperadoPadrao.setValor(null);
		ResponseEntity<List> resposta = requisitarCadastroLancamentoInvalido();

		assertThat(HttpStatus.BAD_REQUEST, is(equalTo(resposta.getStatusCode())));
		assertThat("O valor não pode ser nulo ou zerado.", is(equalTo(obterMensagemDe(resposta))));
	}
}