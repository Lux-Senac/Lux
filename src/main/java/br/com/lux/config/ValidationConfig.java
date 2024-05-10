package br.com.lux.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.xml.validation.Validator;

@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        return (Validator) Validation.buildDefaultValidatorFactory().getValidator();
    }
}
