/*
package br.com.lux.reservation;


import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.Reservation;
import br.com.lux.repository.car.CarRepository;
import br.com.lux.repository.client.ClientRepository;
import br.com.lux.repository.reservation.ReservationRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestDriveReservationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test

    public void testCompleteReservationFlow() {
        // Arrange (Preparar)
        // Criar um carro no banco de dados
        Car car = new Car();
        car.setName("Test Car");
        car.setMotor("V8");
        car.setCil("2 cl");
        car.setAcel("100km");
        car.setHp("90");
        car.setTorque("50kg");
        car.setVelmax("999km/ph");
        car.setDoors(4);
        car.setSeats(5);
        car.setVal("4vls");
        car.setPrice(BigDecimal.valueOf(100000));
        car.setImage("0_0");
        car.setTitle("Carro teste");
        car.setPage(CarPageType.Bmw);
        // ... Colocar porfavor @devMorco outros campos obrigatórios ...
        carRepository.save(car);

        // Criar um cliente no banco de dados
        Client client = new Client();
        client.setNome("Test");
        client.setSobrenome("Client");
        client.setContato(118765432);
        client.setEmail("nengue@gmail");
        client.setCep(0);
        client.setPais("Rua da minha casa");
        // ... Colocar porfavor @devMorco outros campos obrigatórios ...
        clientRepository.save(client);

        // Criar a solicitação HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        // Simulando dados do formulário de reserva
        String body = "clientId=" + client.getId() + "&carId=" + car.getId();
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // Act (Agir)
        // Fazer a solicitação POST para o endpoint de reserva
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/agendar-test-drive/" + car.getId()), // Substitua pela URL correta
                HttpMethod.POST,
                request,
                String.class);

        // Assert (Verificar)
        // Verificar se a resposta tem o status esperado (por exemplo, 200 OK ou um redirect)
        assertEquals(200, response.getStatusCodeValue()); // Ou o código de status esperado

        // Verificar se a reserva foi criada no banco de dados
        List<Reservation> reservations = reservationRepository.findAll();
        assertEquals(1, reservations.size());

        Reservation reservation = reservations.get(0);
        assertEquals(client, reservation.getClient());
        assertEquals(car, reservation.getCar());
        // ... verifique outros campos da reserva ...
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
*/