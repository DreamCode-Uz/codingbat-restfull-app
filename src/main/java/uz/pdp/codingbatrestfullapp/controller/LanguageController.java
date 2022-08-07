package uz.pdp.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapp.entity.Language;
import uz.pdp.codingbatrestfullapp.service.LanguageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAllLanguage();
    }

    @GetMapping("/id={languageId}")
    public ResponseEntity<?> getOne(@PathVariable("languageId") Integer id) {
        return service.getOneLanguage(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Language language) {
        return service.addLanguage(language);
    }

    @PutMapping("/id={languageId}")
    public ResponseEntity<?> update(@PathVariable("languageId") Integer id,@Valid @RequestBody Language language) {
        return service.editLanguage(id, language);
    }

    @DeleteMapping("/id={languageId}")
    public ResponseEntity<?> delete(@PathVariable("languageId") Integer id) {
        return service.deleteLanguage(id);
    }
}
