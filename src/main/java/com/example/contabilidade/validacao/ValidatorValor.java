package com.example.contabilidade.validacao;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorValor implements ConstraintValidator<Valor, BigDecimal> {

	@Override
	public boolean isValid(BigDecimal numero, ConstraintValidatorContext cxt) {
		return numero != null && numero.compareTo(new BigDecimal(0)) != 0;
	}
}
