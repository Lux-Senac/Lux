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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

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
        try
        {
            Reservation reservation = new Reservation();

            reservation.setClient(client);
            reservation.setCar(car);
            reservation.setDatareserva(LocalDate.from(LocalDateTime.now()));
            reservation.setStatusreserva(ReservationStatus.ESPERA);
            reservation.setTiporeserva(testDrive);

            reservationRepository.save(reservation);
        }
        catch (Exception e)
        {
            System.out.println("Erro ao registrar reserva: " + e.getMessage());
        }
    }

    @Override
    public void registerReservation(Reservation reservation)
    {
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Reservation> findAllReservations()
    {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Integer id)
    {
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Reservation findReservationById(Integer id)
    {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countByStatusreserva(ReservationStatus statusreserva)
    {
        return reservationRepository.countByStatusreserva(statusreserva);
    }
}
