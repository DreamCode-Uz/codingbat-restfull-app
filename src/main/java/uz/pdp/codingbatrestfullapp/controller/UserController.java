package uz.pdp.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatrestfullapp.entity.User;
import uz.pdp.codingbatrestfullapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getAllUsers(page, size);
    }

    @GetMapping("/id={userId}")
    public ResponseEntity<?> getOne(@PathVariable("userId") Integer id) {
        return service.getOneUser(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return service.addNewUser(user);
    }

    @PutMapping("/id={userId}")
    public ResponseEntity<?> update(@PathVariable("userId") Integer id, @Valid @RequestBody User user) {
        return service.editUser(id, user);
    }

    @DeleteMapping("/id={userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Integer id) {
        return service.deleteUser(id);
    }
}
