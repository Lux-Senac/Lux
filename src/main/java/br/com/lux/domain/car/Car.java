package br.com.lux.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Car")
@Table(name = "Car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String motor;

    @Column
    private String cil;

    @Column
    private String acel;

    @Column
    private String hp;

    @Column
    private String torque;

    @Column
    private String velmax;

    @Column
    private Integer doors;

    @Column
    private Integer seats;

    @Column
    private String val;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column
    private String image;

    @Column
    private String title;

    @Column
    private String page;
}
