package uz.pdp.codingbatrestfullapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.User;
import uz.pdp.codingbatrestfullapp.exception.ConflictException;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.repository.UserRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> getAllUsers(Integer page, Integer size) {
        return ok(repository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10)));
    }

    public ResponseEntity<?> getOneUser(Integer userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (!optionalUser.isPresent()) throw new RecordNotFoundException("User id not found");
        return ok(optionalUser.get());
    }

    public ResponseEntity<?> addNewUser(User user) {
        if (repository.existsByEmail(user.getEmail())) throw new RecordNotFoundException("User email conflicted");
        return status(HttpStatus.CREATED).body(repository.save(user));
    }

    public ResponseEntity<?> editUser(Integer userId, User user) {
        Optional<User> optionalUser = repository.findById(userId);
        if (!optionalUser.isPresent()) throw new RecordNotFoundException("User id not found");
        if (repository.existsByEmailAndIdNot(user.getEmail(), userId))
            throw new ConflictException("User email conflicted");
        User u = optionalUser.get();
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        return ok(repository.save(u));
    }

    public ResponseEntity<?> deleteUser(Integer userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (!optionalUser.isPresent()) throw new RecordNotFoundException("User id not found");
        repository.delete(optionalUser.get());
        return ok("User successfully deleted");
    }
}
