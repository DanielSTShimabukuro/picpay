package picpay.picpay.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import picpay.picpay.dtos.exception.ExceptionDTO;
import picpay.picpay.exceptions.BusinessException;

@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionDTO> handleBusinessException(BusinessException ex) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

    return ResponseEntity.badRequest().body(exceptionDTO);
  }
}
