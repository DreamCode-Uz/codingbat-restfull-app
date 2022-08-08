package uz.pdp.codingbatrestfullapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatrestfullapp.entity.Answer;
import uz.pdp.codingbatrestfullapp.entity.Task;
import uz.pdp.codingbatrestfullapp.entity.User;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.payload.AnswerDTO;
import uz.pdp.codingbatrestfullapp.payload.response.ApiError;
import uz.pdp.codingbatrestfullapp.repository.AnswerRepository;
import uz.pdp.codingbatrestfullapp.repository.TaskRepository;
import uz.pdp.codingbatrestfullapp.repository.UserRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Service
public class AnswerService {
    private final AnswerRepository repository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnswerService(AnswerRepository repository, TaskRepository taskRepository, UserRepository userRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getAll(Integer userId) {
        Optional<Answer> optionalAnswer = repository.findByUsers_Id(userId);
        if (!optionalAnswer.isPresent()) throw new RecordNotFoundException("User id not found");
        return ok(optionalAnswer.get());
    }

    public ResponseEntity<?> getOne(Integer answerId) {
        Optional<Answer> optionalAnswer = repository.findById(answerId);
        if (!optionalAnswer.isPresent()) throw new RecordNotFoundException("Answer not found");
        return ok(optionalAnswer.get());
    }

    public ResponseEntity<?> addAnswer(AnswerDTO dto) {
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()) throw new RecordNotFoundException("Task id not found");
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (!optionalUser.isPresent()) throw new RecordNotFoundException("User id not found");
        Answer save = repository.save(new Answer(dto.getResult(), optionalTask.get(), optionalUser.get(), false));
        return status(HttpStatus.CREATED).body(save);
    }

    public ResponseEntity<?> editAnswer(Integer id, AnswerDTO dto) {
        Optional<Answer> optionalAnswer = repository.findById(id);
        if (!optionalAnswer.isPresent()) throw new RecordNotFoundException("Answer not found");
        if (!repository.existsByTask_IdAndUsers_Id(dto.getTaskId(), dto.getUserId()))
            return new ResponseEntity<>(new ApiError(BAD_REQUEST, "Validation failed", "TaskId and UserId not correct"), BAD_REQUEST);
        Answer answer = optionalAnswer.get();
//        User o'zi kiritgan qiymat yangilayotgan faqat natijasi va natija javobigina o'zgaradi
        answer.setResult(dto.getResult());
        answer.setCorrect(true);
        return ok(repository.save(answer));
    }
/*
//  Codingbat siteda berilgan javobni tozalab yuborishni iloji yo'q
    public ResponseEntity<?> deleteAnswer(Integer answerId) {
        Optional<Answer> optionalAnswer = repository.findById(answerId);
        if (!optionalAnswer.isPresent()) throw new RecordNotFoundException("Answer not found");
        repository.delete(optionalAnswer.get());
        return ok("Answer successfully deleted");
    }
*/
}