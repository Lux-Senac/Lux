package br.com.lux.domain.car;

public enum CarPageType
{
    Bmw, BYDTan, BYDYuan, Porsche, TeslaModelS, TeslaModelX;

    @Override
    public String toString() {
        return name();
    }
}
