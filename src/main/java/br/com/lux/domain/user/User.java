package br.com.lux.domain.user;

import br.com.lux.domain.client.Client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;

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

    @NotBlank(message = "O campo username é obrigatório!")
    @Size(min = 6, message = "O username deve ter no mínimo 6 caracteres!")
    @Size(max = 20, message = "O username deve ter no máximo 20 caracteres!")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "O campo password é obrigatório!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial!")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O campo nome é obrigatório!")
    @Email(message = "O campo email é inválido!")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres!")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String urlavatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType tipo;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", unique = true)
    private Client cliente;
}
