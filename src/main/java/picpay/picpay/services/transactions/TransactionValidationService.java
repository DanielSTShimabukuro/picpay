package picpay.picpay.services.transactions;

import org.springframework.stereotype.Service;

import picpay.picpay.dtos.transactions.TransactionRequestDTO;
import picpay.picpay.exceptions.BusinessException;
import picpay.picpay.models.user.User;

@Service
public class TransactionValidationService {
  public void validateTransaction(User sender, TransactionRequestDTO request) {
    if (request.senderId().equals(request.receiverId())) {
      throw new BusinessException("Sender and receiver must not be the same user");
    }

    if (sender.getBalance().compareTo(request.amount()) < 0) {
      throw new BusinessException("Sender balance insuficient");
    }
  }
}
