package uz.pdp.codingbatrestfullapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.Example;
import uz.pdp.codingbatrestfullapp.entity.Task;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.payload.ExampleDTO;
import uz.pdp.codingbatrestfullapp.repository.ExampleRepository;
import uz.pdp.codingbatrestfullapp.repository.TaskRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class ExampleService {
    private final ExampleRepository repository;
    private final TaskRepository taskRepository;

    @Autowired
    public ExampleService(ExampleRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<?> getAllExampleForPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10);
        return ok(repository.findAll(pageRequest));
    }

    public ResponseEntity<?> getAll(Integer langId, Integer categoryId) {
        return ok(repository.getAllByTask_CategoryIdAndTask_LanguageId(categoryId, langId));
    }

    public ResponseEntity<?> getOneExample(Integer id) {
        Optional<Example> optionalExample = repository.findById(id);
        if (!optionalExample.isPresent()) throw new RecordNotFoundException("Example not found");
        return ok(optionalExample.get());
    }

    public ResponseEntity<?> addExample(ExampleDTO dto) {
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()) throw new RecordNotFoundException("TAsk not found");
        Example save = repository.save(new Example(dto.getText(), optionalTask.get()));
        return status(HttpStatus.CREATED).body(save);
    }

    public ResponseEntity<?> editExample(Integer id, ExampleDTO dto) {
        Optional<Example> optionalExample = repository.findById(id);
        if (!optionalExample.isPresent()) throw new RecordNotFoundException("Example not found");
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()) throw new RecordNotFoundException("Task not found");
        Example example = optionalExample.get();
        example.setText(dto.getText());
        example.setTask(optionalTask.get());
        return ok(repository.save(example));
    }

    public ResponseEntity<?> deleteExample(Integer id) {
        Optional<Example> optionalExample = repository.findById(id);
        if (!optionalExample.isPresent()) throw new RecordNotFoundException("Example not found");
        repository.delete(optionalExample.get());
        return ok("Example successfully deleted");
    }
}
