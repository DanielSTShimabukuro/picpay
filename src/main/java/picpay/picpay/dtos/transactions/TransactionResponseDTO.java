package picpay.picpay.dtos.transactions;

import java.math.BigDecimal;

public record TransactionResponseDTO(String id, String senderId, String receiverId, BigDecimal amount) {
  
}
