package br.com.lux.domain.client;

import br.com.lux.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactPreference preferenciacontato;

    @NotBlank(message = "O campo Contado é obrigatório.")
    @Column
    private String contato;

    @NotBlank(message = "O campo Email é obrigatório.")
    @Column
    private String email;

    @NotBlank(message = "O campo CEP é obrigatório.")
    @Column(nullable = false)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Countries pais;

    @OneToMany(mappedBy = "cliente")
    private List<User> users;
}