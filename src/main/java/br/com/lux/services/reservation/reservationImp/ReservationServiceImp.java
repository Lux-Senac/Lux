package br.com.lux.services.reservation.reservationImp;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.domain.reservation.ReservationType;
import br.com.lux.repository.reservation.ReservationRepository;
import br.com.lux.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReservationServiceImp implements ReservationService
{
    @Autowired
    private final ReservationRepository reservationRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository)
    {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void registerReservation(Client client, Car car, ReservationType testDrive)
    {
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setCar(car);
        reservation.setDatareserva(LocalDate.from(LocalDateTime.now()));
        reservation.setStatusreserva(ReservationStatus.ESPERA);
        reservation.setTiporeserva(testDrive);
        reservationRepository.save(reservation);
    }
}
