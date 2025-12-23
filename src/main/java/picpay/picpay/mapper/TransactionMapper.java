package picpay.picpay.mapper;

import org.springframework.stereotype.Component;

import picpay.picpay.dtos.transactions.TransactionRequestDTO;
import picpay.picpay.models.transactions.Transaction;

@Component
public class TransactionMapper {
  public Transaction toEntity(TransactionRequestDTO request) {
    Transaction transaction = new Transaction();

    transaction.setAmount(request.amount());

    return transaction;
  }
}
