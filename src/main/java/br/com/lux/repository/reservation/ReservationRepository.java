package br.com.lux.repository.reservation;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    long countByStatusreserva(ReservationStatus statusreserva);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Page<Reservation> findByClientNomeContainingIgnoreCase(String searchTerm, Pageable pageable);
}
