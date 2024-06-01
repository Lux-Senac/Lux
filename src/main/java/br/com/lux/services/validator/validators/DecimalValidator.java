package br.com.lux.services.validator.validators;

import br.com.lux.services.validator.annotations.DecimalAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class DecimalValidator implements ConstraintValidator<DecimalAnnotation, BigDecimal>
{
    @Override
    public void initialize(DecimalAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            new BigDecimal(value.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

