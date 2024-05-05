package br.com.lux.services.reservation;


import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.ReservationType;

public interface ReservationService
{
    void registerReservation(Client client, Car car, ReservationType testDrive);
}
