package br.com.lux.domain.user;

import br.com.lux.domain.client.Client;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "USR")
@Table(name = "USR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O campo username é obrigatório!")
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "O campo password é obrigatório!")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "O campo email é obrigatório!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "O campo urlavatar é obrigatório!")
    @Column(nullable = false)
    private String urlavatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType tipo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;
}
