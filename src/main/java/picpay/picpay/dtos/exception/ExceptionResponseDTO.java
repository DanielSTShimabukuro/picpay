package picpay.picpay.dtos.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionResponseDTO(
  int status,
  List<String> message,
  LocalDateTime timestamp) {
  
}
