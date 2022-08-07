package uz.pdp.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapp.payload.CategoryDTO;
import uz.pdp.codingbatrestfullapp.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        return service.getAllCategory();
    }

    @GetMapping("/id={categoryId}")
    private ResponseEntity<?> getOne(@PathVariable("categoryId") Integer id) {
        return service.getOneCategory(id);
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid@RequestBody CategoryDTO categoryDTO) {
        return service.addCategory(categoryDTO);
    }

    @PutMapping("/id={categoryId}")
    private ResponseEntity<?> update(@PathVariable("categoryId") Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return service.editCategory(id, categoryDTO);
    }

    @DeleteMapping("/id={categoryId}")
    private ResponseEntity<?> delete(@PathVariable("categoryId") Integer id) {
        return service.deleteCategory(id);
    }
}
