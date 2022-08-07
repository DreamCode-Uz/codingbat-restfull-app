package uz.pdp.codingbatrestfullapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatrestfullapp.entity.Example;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Integer> {

    List<Example> getAllByTask_CategoryIdAndTask_LanguageId(Integer task_category_id, Integer task_language_id);
}
