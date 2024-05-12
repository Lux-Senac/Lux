package br.com.lux.domain.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
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

    @NotNull(message = "Data da venda é obrigatória.")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datavenda;

    @NotNull(message = "Preço de venda é obrigatório.")
    @Column(precision = 10, scale = 2)
    private BigDecimal precovenda;
}
