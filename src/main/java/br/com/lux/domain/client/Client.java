package br.com.lux.domain.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;

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
    private Integer id;

    @NotBlank(message = "O campo Nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O campo SobreNome é obrigatório.")
    @Column(nullable = false)
    private String sobrenome;

    @NotNull(message = "O campo Contado é obrigatório.")
    @Column
    private int contato;

    @NotNull(message = "O campo Email é obrigatório.")
    @Column
    private String email;

    @NotNull(message = "O campo CEP é obrigatório.")
    @Column(nullable = false)
    private int cep;

    @NotBlank(message = "O campo Pais é obrigatório.")
    @Column(nullable = false)
    private String pais;

}