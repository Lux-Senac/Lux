package br.com.lux.services.validator.annotations;

import br.com.lux.services.validator.validators.DecimalValidator;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DecimalValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalAnnotation
{
    String message() default "O valor deve ser um número";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
