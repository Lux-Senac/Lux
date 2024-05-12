package br.com.lux.domain.reservation;

public enum ReservationType
{
    TESTDRIVE, RESERVA;

    @Override
    public String toString()
    {
        return name();
    }
}
