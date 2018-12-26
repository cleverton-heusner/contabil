package com.example.contabilidade.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class ManipuladorExcecoesHttp extends ResponseEntityExceptionHandler {

	private final String MENSAGEM_CAMINHO_RECURSO_INVALIDO = "http_400.caminho_recurso_invalido";
	private final String MENSAGEM_CAMPOS_INVALIDOS = "http_400.campos_invalidos";
	private final String MENSAGEM_FILTRO_INVALIDO = "http_400.filtro_invalido";
	private final String MENSAGEM_PARAM_NAO_SUPORTADO = "http_405.param_nao_suportado";

	@Autowired
	private Mensagens mensagens;

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String mensagemCaminhoRecursoInvalido = mensagens.get(MENSAGEM_CAMINHO_RECURSO_INVALIDO);
		return new ResponseEntity<Object>(mensagemCaminhoRecursoInvalido, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> camposInvalidos = new ArrayList<>();

		if (ex.getCause() instanceof JsonMappingException) {
			JsonMappingException e = (JsonMappingException) ex.getCause();
			e.getPath().forEach(reference -> camposInvalidos.add(reference.getFieldName()));
		}

		String mensagemCamposInvalidos = mensagens.get(MENSAGEM_CAMPOS_INVALIDOS);
		String mensagem = mensagemCamposInvalidos + System.lineSeparator() + camposInvalidos;
		return new ResponseEntity<Object>(mensagem, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemFiltroInvalido = mensagens.get(MENSAGEM_FILTRO_INVALIDO);
		return new ResponseEntity<Object>(mensagemFiltroInvalido, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> mensagensErro = new ArrayList<>();
		List<ObjectError> erros = ex.getBindingResult().getAllErrors();
		erros.forEach(erro -> mensagensErro.add(erro.getDefaultMessage()));

		return new ResponseEntity<Object>(mensagensErro, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemParamNaoSuportado = mensagens.get(MENSAGEM_PARAM_NAO_SUPORTADO);
		return new ResponseEntity<Object>(mensagemParamNaoSuportado, status);
	}
}
