package br.com.lux.services.reservation;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.domain.reservation.ReservationType;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService
{
    void registerReservation(Client client, Car car, ReservationType testDrive);

    void registerReservation(Reservation reservation);

    List<Reservation> findAllReservations();

    void deleteReservation(Integer id);

    Reservation findReservationById(Integer id);

    long countByStatusreserva(ReservationStatus statusreserva);

    Page<Reservation> findReservationAll(int page,int size);

    Page<Reservation> searchReservations(String searchTerm, int page, int size);
}
