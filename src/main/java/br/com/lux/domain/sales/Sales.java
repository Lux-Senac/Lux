package br.com.lux.domain.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Vendas")
@Table(name = "Vendas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sales
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "iD do carro é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    private Car carro;

    @NotNull(message = "iD do cliente é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;

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

    @NotNull(message = "Preço de venda é obrigatório.")
    @Column(precision = 10, scale = 2)
    private BigDecimal precovenda;
}