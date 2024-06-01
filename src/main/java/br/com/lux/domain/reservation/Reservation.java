package br.com.lux.domain.reservation;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "id_car", nullable = false)
    private Car car;

    @JsonManagedReference
    @ManyToOne()
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

