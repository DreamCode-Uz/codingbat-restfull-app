package uz.pdp.codingbatrestfullapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Email(message = "User email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "User password should be null")
    @Size(message = "Your password must be at least 6 characters long.", min = 6)
    @Column(nullable = false)
    private String password;
}
