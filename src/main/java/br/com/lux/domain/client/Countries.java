package br.com.lux.domain.client;

public enum Countries {
    ARGENTINA,
    BOLIVIA,
    BRASIL,
    CHILE,
    COLOMBIA,
    EQUADOR,
    GUIANA,
    PARAGUAI,
    PERU,
    SURINAME,
    URUGUAI,
    VENEZUELA;

    @Override
    public String toString() {
        return name();
    }
}
