package uz.pdp.codingbatrestfullapp.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.codingbatrestfullapp.exception.RecordNotFoundException;
import uz.pdp.codingbatrestfullapp.payload.response.ApiError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        ApiError error = new ApiError(INTERNAL_SERVER_ERROR, "Server error", e.getLocalizedMessage());
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<?> handleNotFoundException(RecordNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(NOT_FOUND, "Record Not Found", e.getLocalizedMessage()), NOT_FOUND);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<?> handleConflictException(RecordNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(CONFLICT, "Data integrity violation", e.getLocalizedMessage()), CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(v -> builder.append(v).append(" "));
        ApiError error = new ApiError(METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString().trim());
        return new ResponseEntity<>(error, METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        ApiError error = new ApiError(BAD_REQUEST, "Validation Failed", errors);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
