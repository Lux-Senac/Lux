package br.com.lux.domain.rating;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "Avaliacoes")
@Table(name = "Avaliacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    private Car carro;

    @Column(nullable = false)
    private int avaliacao;

    @Column
    private String feedback;
}
