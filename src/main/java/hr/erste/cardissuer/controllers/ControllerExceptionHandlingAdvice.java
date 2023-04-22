package hr.erste.cardissuer.controllers;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author d315089
 * @Date 2.3.2023.
 */
@Slf4j
@ControllerAdvice
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ControllerExceptionHandlingAdvice {
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Incorrect method parameters exception", e);
        return new ResponseEntity<>("Neispravni parametri poziva", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<Object> handleMethodArgumentTypeException(MethodArgumentTypeMismatchException e) {
        log.error("Wrong method parameters exception", e);
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleSystemException(Exception e) {
        log.error("System Exception", e);
        return ResponseEntity.internalServerError().build();
    }

}
