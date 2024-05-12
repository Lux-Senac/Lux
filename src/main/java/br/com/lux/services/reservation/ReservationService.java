package br.com.lux.services.reservation;


import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationType;

import java.util.List;

public interface ReservationService
{
    void registerReservation(Client client, Car car, ReservationType testDrive);

    void registerReservation(Reservation reservation);

    List<Reservation> findAllReservations();

    void deleteReservation(Integer id);
}
