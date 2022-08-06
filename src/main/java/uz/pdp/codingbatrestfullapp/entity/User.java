package uz.pdp.codingbatrestfullapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 6, message = "Your password must be at least 6 characters long.")
    @Column(nullable = false)
    private String password;
}
