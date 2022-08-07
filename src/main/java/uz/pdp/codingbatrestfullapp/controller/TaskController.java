package uz.pdp.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapp.entity.Language;
import uz.pdp.codingbatrestfullapp.payload.TaskDTO;
import uz.pdp.codingbatrestfullapp.service.LanguageService;
import uz.pdp.codingbatrestfullapp.service.TaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/category={categoryId}")
    public ResponseEntity<?> getAll(@PathVariable("categoryId") Integer categoryId) {
        return service.getAll(categoryId);
    }

    @GetMapping("/category={categoryId}/language={languageId}")
    public ResponseEntity<?> getAll(@PathVariable("categoryId") Integer categoryId,
                                    @PathVariable("languageId") Integer languageId) {
        return service.getAll(categoryId, languageId);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TaskDTO taskDTO) {
        return service.addTask(taskDTO);
    }

    @PutMapping("/id={taskId}")
    public ResponseEntity<?> update(@PathVariable("taskId") Integer id, @Valid @RequestBody TaskDTO taskDTO) {
        return service.editTask(id, taskDTO);
    }

    @DeleteMapping("/id={taskId}")
    public ResponseEntity<?> delete(@PathVariable("taskId") Integer id) {
        return service.deleteTask(id);
    }
}
