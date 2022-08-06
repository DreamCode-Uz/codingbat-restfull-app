package uz.pdp.codingbatrestfullapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatrestfullapp.entity.Language;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(@NotNull @Size(message = "Language name should not be less than 2 characters.") String name);

    boolean existsByNameAndIdNot(@NotNull @Size(message = "Language name should not be less than 2 characters.") String name, Integer id);
}
