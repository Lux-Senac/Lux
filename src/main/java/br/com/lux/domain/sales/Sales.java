package br.com.lux.domain.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
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
public class    Sales
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    private Car carro;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_venda;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco_venda;
}
