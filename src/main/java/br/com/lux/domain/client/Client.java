package br.com.lux.domain.client;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
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
    @Size(max = 50, message = "O campo Nome deve ter no máximo 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O campo Nome deve conter apenas letras.")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "O campo SobreNome é obrigatório.")
    @Size(max = 50, message = "O campo SobreNome deve ter no máximo 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O campo Nome deve conter apenas letras.")
    @Column(nullable = false, length = 50)
    private String sobrenome;

    @NotNull(message = "A preferência de contato é obrigatória.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactPreference preferenciacontato;

    @NotBlank(message = "O campo Contado é obrigatório.")
    @Size(max = 20, message = "O campo Contado deve ter no máximo 20 caracteres.")
    @Column(length = 20)
    private String contato;

    @NotBlank(message = "O campo Email é obrigatório.")
    @Email(message = "O campo email é inválido.")
    @Size(max = 100, message = "O campo Email deve ter no máximo 100 caracteres.")
    @Column(length = 100)
    private String email;

    @NotBlank(message = "O campo CEP é obrigatório.")
    @Column(nullable = false)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Countries pais;

    @JsonBackReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH)
    private List<User> users;

    @JsonBackReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    @JsonBackReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    private List<Sales> sales;
}