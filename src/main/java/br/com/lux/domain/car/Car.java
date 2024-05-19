package br.com.lux.domain.car;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.sales.Sales;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Car")
@Table(name = "Car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do carro é obrigatório.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O motor do carro é obrigatório.")
    @Column(nullable = false)
    private String motor;

    @NotBlank(message = "O Cilindro do carro é obrigatório.")
    @Column(nullable = false)
    private String cil;

    @NotBlank(message = "A Aceleração do carro é obrigatória.")
    @Column(nullable = false)
    private String acel;

    @NotBlank(message = "A Potência do carro é obrigatória.")
    @Column(nullable = false)
    private String hp;

    @NotBlank(message = "O Torque do carro é obrigatório.")
    @Column(nullable = false)
    private String torque;

    @NotBlank(message = "A Velocidade máxima do carro é obrigatória.")
    @Column(nullable = false)
    private String velmax;

    @NotNull(message = "Numero de portas do carro é obrigatório.")
    @DecimalMin(value = "1.9", message = "O numero de portas do carro não pode ser menor que 2")
    @Column(nullable = false)
    private Integer doors;

    @NotNull(message = "Numero de assentos do carro é obrigatório.")
    @DecimalMin(value = "1.9", message = "O numero de assentos do carro não pode ser menor que 2")
    @Column(nullable = false)
    private Integer seats;

    @NotBlank(message = "Coloquei o valor da válvula do carro ou tipo da tração.")
    @Column(nullable = false)
    private String val;

    @NotNull(message = "O preço do carro é obrigatório.")
    @DecimalMin(value = "0.1", message = "O preço do carro não pode ser menor que 0")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotBlank(message = "A URL da imagem do carro é obrigatória.")
    @Column(nullable = false)
    private String image;

    @NotBlank(message = "A descrição do carro é obrigatória.")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "A pagina do carro é obrigatória.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarPageType page;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sales> sales = new ArrayList<>();
}
