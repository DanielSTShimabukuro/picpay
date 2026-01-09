package picpay.picpay.configs;

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
    ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
    ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .toList();

    String message = String.join("; ", errors);
    ExceptionResponseDTO response = new ExceptionResponseDTO(message, HttpStatus.BAD_REQUEST.value());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
