package uz.pdp.codingbatrestfullapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.Category;
import uz.pdp.codingbatrestfullapp.entity.Language;
import uz.pdp.codingbatrestfullapp.entity.Task;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.payload.TaskDTO;
import uz.pdp.codingbatrestfullapp.repository.CategoryRepository;
import uz.pdp.codingbatrestfullapp.repository.LanguageRepository;
import uz.pdp.codingbatrestfullapp.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final CategoryRepository categoryRepository;

    private final LanguageRepository languageRepository;

    @Autowired
    public TaskService(TaskRepository repository, CategoryRepository categoryRepository, LanguageRepository languageRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
    }

    public ResponseEntity<?> getAll() {
        return (ResponseEntity<?>) repository.findAll();
    }

    public ResponseEntity<?> getAll(Integer categoryId) {
        if (!categoryRepository.findById(categoryId).isPresent())
            throw new RecordNotFoundException("Category not found");
        return ok(repository.getAllByCategory_Id(categoryId));
    }

    public ResponseEntity<?> getAll(Integer categoryId, Integer languageId) {
        List<Task> tasks = repository.getAllByCategory_IdAndLanguage_Id(categoryId, languageId);
        return ok(tasks);
    }

    public ResponseEntity<?> addTask(TaskDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) throw new RecordNotFoundException("Category not found");
        Optional<Language> optionalLanguage = languageRepository.findById(dto.getLanguageId());
        if (!optionalLanguage.isPresent()) throw new RecordNotFoundException("Language not found");
        Task task = new Task(
                dto.getName(), dto.getDescription(), dto.getSolution(), dto.getHint(), dto.getMethod(), dto.isHasStar(),
                optionalCategory.get(), optionalLanguage.get()
        );
        return status(HttpStatus.CREATED).body(repository.save(task));
    }

    public ResponseEntity<?> editTask(Integer id, TaskDTO dto) {
        Optional<Task> optionalTask = repository.findById(id);
        if (!optionalTask.isPresent()) throw new RecordNotFoundException("Task not found");
//        not duplicate
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) throw new RecordNotFoundException("Category not found");
        Optional<Language> optionalLanguage = languageRepository.findById(dto.getLanguageId());
        if (!optionalLanguage.isPresent()) throw new RecordNotFoundException("Language not found");
        Task task = new Task(id, dto.getName(), dto.getDescription(), dto.getSolution(), dto.getHint(), dto.getMethod(), dto.isHasStar(), optionalCategory.get(), optionalLanguage.get());
        Task save = repository.save(task);
        return ok(save);
    }

    public ResponseEntity<?> deleteTask(Integer id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (!optionalTask.isPresent()) throw new RecordNotFoundException("Task not found");
        repository.delete(optionalTask.get());
        return ok("Task successfully deleted");
    }
}
