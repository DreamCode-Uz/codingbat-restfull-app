package uz.pdp.codingbatrestfullapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatrestfullapp.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findAllByCategory_Id(Integer category_id);

    List<Task> getAllByCategory_Id(Integer category_id);

    List<Task> getAllByCategory_IdAndLanguage_Id(Integer category_id, Integer language_id);
}
