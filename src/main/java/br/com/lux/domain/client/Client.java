package br.com.lux.domain.client;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@Entity(name = "Clientes")
@Table(name = "Clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O campo Nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O campo SobreNome é obrigatório.")
    @Column(nullable = false)
    private String sobrenome;

    @NotNull(message = "O campo CPF é obrigatório.")
    @Column(nullable = false)
    private int contato;

    @Column()
    private String email;

    @NotNull(message = "O campo CEP é obrigatório.")
    @Column(nullable = false)
    private int cep;

    @NotBlank(message = "O campo Endereço é obrigatório.")
    @Column(nullable = false)
    private String pais;
}
