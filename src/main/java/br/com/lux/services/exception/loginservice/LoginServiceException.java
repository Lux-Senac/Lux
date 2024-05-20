package br.com.lux.services.exception.loginservice;

public class LoginServiceException extends RuntimeException {
    public LoginServiceException(String message) {
        super(message);
    }
}