package br.com.lux.domain.client;

public enum ContactPreference
{
    EMAIL,
    TELEFONE,
    WHATSAPP;

    @Override
    public String toString() {
        return name();
    }
}
