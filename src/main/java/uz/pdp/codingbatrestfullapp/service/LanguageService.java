package uz.pdp.codingbatrestfullapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.Language;
import uz.pdp.codingbatrestfullapp.exception.ConflictException;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.repository.LanguageRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class LanguageService {
    private final LanguageRepository repository;

    @Autowired
    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> getAllLanguage() {
        return ok(repository.findAll());
    }

    public ResponseEntity<?> getOneLanguage(Integer languageId) {
        Optional<Language> optionalLanguage = repository.findById(languageId);
        if (!optionalLanguage.isPresent()) throw new RecordNotFoundException("Language id not found");
        return ok(optionalLanguage.get());
    }

    public ResponseEntity<?> addLanguage(Language language) {
        if (repository.existsByName(language.getName())) throw new ConflictException("Language name conflicted");
        return status(HttpStatus.CREATED).body(repository.save(language));
    }

    public ResponseEntity<?> editLanguage(Integer languageId, Language language) {
        Optional<Language> optionalLanguage = repository.findById(languageId);
        if (!optionalLanguage.isPresent()) throw new RecordNotFoundException("Language id not found");
        if (repository.existsByNameAndIdNot(language.getName(), languageId))
            throw new ConflictException("Language name conflicted");
        Language lang = optionalLanguage.get();
        lang.setName(language.getName());
        return ok(repository.save(lang));
    }

    public ResponseEntity<?> deleteLanguage(Integer languageId) {
        Optional<Language> optionalLanguage = repository.findById(languageId);
        if (!optionalLanguage.isPresent()) throw new RecordNotFoundException("Language id not found");
        repository.delete(optionalLanguage.get());
        return ok("Language successfully deleted");
    }
}
