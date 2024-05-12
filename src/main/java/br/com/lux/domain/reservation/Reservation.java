package br.com.lux.domain.reservation;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "Reserva")
@Table(name = "Reserva")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_car", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationType tiporeserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus statusreserva;

    @Column(nullable = false)
    private LocalDate datareserva;
}

