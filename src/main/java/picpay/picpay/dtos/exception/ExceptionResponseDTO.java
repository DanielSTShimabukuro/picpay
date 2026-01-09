package picpay.picpay.dtos.exception;

public record ExceptionResponseDTO(
  String message, 
  int statusCode) {
  
}
