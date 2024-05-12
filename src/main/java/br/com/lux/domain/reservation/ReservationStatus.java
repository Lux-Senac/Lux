package br.com.lux.domain.reservation;

public enum ReservationStatus
{
    ESPERA, APROVADO, REJEITADO;

    @Override
    public String toString()
    {
        return name();
    }
}
