package picpay.picpay.dtos.transactions;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestDTO(
  @NotBlank(message = "senderId is required")
  String senderId,

  @NotBlank(message = "receiverId is required")
  String receiverId,

  @NotNull(message = "Amount is required.") 
  @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be equal to or greater than 0.") 
  BigDecimal amount) {
  
}
