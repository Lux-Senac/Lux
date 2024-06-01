package br.com.lux.domain.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Vendas")
@Table(name = "Vendas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sales implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonManagedReference
    @NotNull(message = "iD do carro é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    private Car carro;

    @JsonManagedReference
    @NotNull(message = "iD do cliente é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;

    @JsonManagedReference
    @NotNull(message = "iD do Usuario é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User usuario;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "A data de venda não pode ser nula")
    @PastOrPresent(message = "A data de venda não pode ser uma data futura")
    private Date datavenda;

    @NotNull(message = "O preço do carro é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço do carro não pode ser menor que 0")
    @DecimalMax(value = "19999999.99", message = "O preço do carro não pode ser maior" +
            " que " +
            "19999999.99")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precovenda;
}