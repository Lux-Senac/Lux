package br.com.lux.domain.car;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.sales.Sales;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    @Size(max = 50, message = "O nome do carro deve ter no máximo 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O nome do carro deve conter apenas letras.")
    @Column(nullable = false, length = 50)
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
    @DecimalMax(value = "4.9", message = "O numero de portas do carro não pode ser " +
            "maior que 5")
    @Column(nullable = false)
    private Integer doors;

    @NotNull(message = "Numero de assentos do carro é obrigatório.")
    @DecimalMin(value = "1.9", message = "O numero de assentos do carro não pode ser menor que 2")
    @DecimalMax(value = "6.9", message = "O número de assentos do carro não pode ser " +
            "maior que 7")
    @Column(nullable = false)
    private Integer seats;

    @NotBlank(message = "Coloquei o valor da válvula do carro ou tipo da tração.")
    @Column(nullable = false)
    private String val;

    @NotNull(message = "O preço do carro é obrigatório.")
    @DecimalMin(value = "0.1", message = "O preço do carro não pode ser menor que 0")
    @DecimalMax(value = "19.999.999.99", message = "O preço do carro não pode ser maior" +
            " que " +
            "999999.99")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotBlank(message = "A URL da imagem do carro é obrigatória.")
    @Pattern(regexp = "(?:(ftp|http[s]?:[//])?)?([w]{3}[.])?" +
            "(.*[.](com|php|net|org|br|dk|at|us|tv|info|uk|co.uk|biz|se)?)?" +
            "(.*[.](aspx|htm|html|HTM|HTML|jhtm|jhtml|JHTM|JHTML|xhtm|xhtml|XHTM|XHTML)" +
            "?)?" +
            "(\\w+(\\.(\\w|-)+)*(/(\\w|-)+)*/?)?", message = "A URL da imagem do carro é inválida.")
    @Column(nullable = false)
    private String image;

    @NotBlank(message = "A descrição do carro é obrigatória.")
    @Size(max = 200, message = "A descrição do carro deve ter no máximo 200 caracteres.")
    @Column(nullable = false, length = 200)
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
