package br.com.lux.reservation;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.domain.reservation.ReservationType;
import br.com.lux.repository.reservation.ReservationRepository;
import br.com.lux.services.reservation.ReservationService;
import br.com.lux.services.reservation.reservationImp.ReservationServiceImp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService reservationService;

    @Before
    public void setUp() {
        reservationService = new ReservationServiceImp(reservationRepository); // Substitua pela sua implementação
    }

    @Test
    public void testRegisterReservationSuccess() {
        // Arrange
        Client client = new Client();
        client.setId(1);
        Car car = new Car();
        car.setId(2);

        // Act
        reservationService.registerReservation(client, car, ReservationType.TESTDRIVE);

        // Assert
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository).save(reservationCaptor.capture());
        Reservation savedReservation = reservationCaptor.getValue();
        assertEquals(client, savedReservation.getClient());
        assertEquals(car, savedReservation.getCar());
        assertEquals(ReservationType.TESTDRIVE, savedReservation.getTiporeserva());
        assertEquals(ReservationStatus.ESPERA, savedReservation.getStatusreserva());
        assertNotNull(savedReservation.getDatareserva());
    }

    @Test
    public void testRegisterReservationWithInvalidClient() {
        // Arrange
        Client client = null; // Cliente inválido
        Car car = new Car();
        car.setId(2);

        // Act & Assert
        // Verifique o comportamento esperado para um cliente inválido, como lançar uma exceção ou retornar um valor específico
    }

    @Test
    public void testRegisterReservationWithInvalidCar() {
        // Arrange
        Client client = new Client();
        client.setId(1);
        Car car = null; // Carro inválido

        // Act & Assert
        // Verifique o comportamento esperado para um carro inválido
    }
}