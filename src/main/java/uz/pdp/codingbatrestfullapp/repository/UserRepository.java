package uz.pdp.codingbatrestfullapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatrestfullapp.entity.User;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(@Email(message = "Email should be valid") String email);

    boolean existsByEmailAndIdNot(@Email(message = "Email should be valid") String email, Integer id);
}
