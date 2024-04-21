package br.com.lux.domain.user;

import br.com.lux.domain.client.Client;
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
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserType tipo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Client cliente;
}
