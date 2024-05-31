package br.com.lux.services.reservation.reservationImp;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.domain.reservation.ReservationType;
import br.com.lux.repository.reservation.ReservationRepository;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.reservation.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Transactional
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
        catch(DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao registrar reserva! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void registerReservation(Reservation reservation)
    {
        try
        {
            if(reservation.getDatareserva() == null)
                reservation.setDatareserva(LocalDate.from(LocalDateTime.now()));

            if(reservation.getStatusreserva() == null)
                throw new ServiceException("Status da reserva é obrigatório!");

            if(reservation.getTiporeserva() == null)
                throw new ServiceException("Tipo de reserva é obrigatório!");

            if(reservation.getClient() == null)
                throw new ServiceException("Cliente é obrigatório!");

            if(reservation.getCar() == null)
                throw new ServiceException("Carro é obrigatório!");

            reservationRepository.save(reservation);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao registrar reserva! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Reservation> findAllReservations()
    {
        try
        {
            return reservationRepository.findAll();
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar todas as reservas! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Reservation> findReservationAll(int page, int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            return reservationRepository.findAll(pageable);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar todas as reservas! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Reservation> searchReservations(String searchTerm, int page, int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            return reservationRepository.findByClientNomeContainingIgnoreCase(searchTerm, pageable);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar reservas! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteReservation(Integer id)
    {
        try
        {
            reservationRepository.deleteById(id);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao deletar reserva! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Reservation findReservationById(Integer id)
    {
        try
        {
            return reservationRepository.findById(id).orElse(null);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar reserva por id! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countByStatusreserva(ReservationStatus statusreserva)
    {
        try
        {
            return reservationRepository.countByStatusreserva(statusreserva);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao contar reservas por status! " + e.getMessage());
        }
    }
}
