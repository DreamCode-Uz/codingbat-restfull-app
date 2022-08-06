package uz.pdp.codingbatrestfullapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatrestfullapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
