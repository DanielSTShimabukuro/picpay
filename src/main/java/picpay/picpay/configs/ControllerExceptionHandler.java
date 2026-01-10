package picpay.picpay.configs;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import picpay.picpay.dtos.exception.ExceptionResponseDTO;
import picpay.picpay.exceptions.BusinessException;

@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionResponseDTO> handleBusinessException(BusinessException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ExceptionResponseDTO response = new ExceptionResponseDTO(
      status.value(), 
      List.of(ex.getMessage()), 
      LocalDateTime.now());

    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    ExceptionResponseDTO response = new ExceptionResponseDTO(
      status.value(),
      List.of(ex.getMessage()),
      LocalDateTime.now());

    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .toList();

    ExceptionResponseDTO response = new ExceptionResponseDTO(
      HttpStatus.BAD_REQUEST.value(),
      errors,
      LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponseDTO> handleException(Exception ex) {
    String message = "Internal server error.";

    ExceptionResponseDTO response = new ExceptionResponseDTO(
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      List.of(message),
      LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
