package uz.pdp.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapp.payload.AnswerDTO;
import uz.pdp.codingbatrestfullapp.service.AnswerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService service;

    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @GetMapping("/user={userId}")
    public ResponseEntity<?> getAll(@PathVariable("userId") Integer userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id={answerId}")
    public ResponseEntity<?> getOne(@PathVariable("answerId") Integer id) {
        return service.getOne(id);
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid @RequestBody AnswerDTO answerDTO) {
        return service.addAnswer(answerDTO);
    }

    @PutMapping("/id={answerId}")
    public ResponseEntity<?> update(@PathVariable("answerId") Integer answerId, @Valid @RequestBody AnswerDTO answerDTO) {
        return service.editAnswer(answerId, answerDTO);
    }

    public static void main(String[] args) {
        System.out.println(new StringBuilder("Salom").reverse());
    }
}
