package br.com.lux.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        return (Validator) Validation.buildDefaultValidatorFactory().getValidator();
    }
}
