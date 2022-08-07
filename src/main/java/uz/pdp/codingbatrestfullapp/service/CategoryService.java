package uz.pdp.codingbatrestfullapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.Category;
import uz.pdp.codingbatrestfullapp.entity.Language;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.payload.CategoryDTO;
import uz.pdp.codingbatrestfullapp.repository.CategoryRepository;
import uz.pdp.codingbatrestfullapp.repository.LanguageRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.ResponseEntity.*;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final LanguageRepository languageRepository;

    public CategoryService(CategoryRepository repository, LanguageRepository languageRepository) {
        this.repository = repository;
        this.languageRepository = languageRepository;
    }

    //    READ ALL
    public ResponseEntity<?> getAllCategory() {
        return ok(repository.findAll());
    }

    //    READ ONE
    public ResponseEntity<?> getOneCategory(Integer categoryId) {
        Optional<Category> optionalCategory = repository.findById(categoryId);
        if (!optionalCategory.isPresent()) throw new RecordNotFoundException("Category not found");
        return ok(optionalCategory.get());
    }

    //    CREAT
    public ResponseEntity<?> addCategory(CategoryDTO dto) {
        Set<Language> languages = checkLanguage(dto.getLanguagesId());
        if (languages.size() == 0) throw new RecordNotFoundException("Language id(s) not found");
        return status(HttpStatus.CREATED).body(repository.save(new Category(dto.getName(), languages)));
    }

    public ResponseEntity<?> editCategory(Integer id, CategoryDTO dto) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (!optionalCategory.isPresent()) throw new RecordNotFoundException("Category not found");
        Set<Language> languages = checkLanguage(dto.getLanguagesId());
        if (languages.size() == 0) throw new RecordNotFoundException("Languages id not found");
        Category category = optionalCategory.get();
        category.setName(dto.getName());
        category.setLanguages(languages);
        return ok(repository.save(category));
    }

    public ResponseEntity<?> deleteCategory(Integer categoryId) {
        Optional<Category> optionalCategory = repository.findById(categoryId);
        if (!optionalCategory.isPresent()) throw new RecordNotFoundException("Category not found");
        repository.delete(optionalCategory.get());
        return ok("Category successfully deleted");
    }

    //    ACTION
    public Set<Language> checkLanguage(Set<Integer> ids) {
        Set<Language> languages = new HashSet<>();
        for (Integer id : ids) {
            Optional<Language> optionalLanguage = languageRepository.findById(id);
            optionalLanguage.ifPresent(languages::add);
        }
        return languages;
    }
}
