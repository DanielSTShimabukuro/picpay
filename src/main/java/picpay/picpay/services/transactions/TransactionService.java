package picpay.picpay.services.transactions;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import picpay.picpay.dtos.transactions.TransactionRequestDTO;
import picpay.picpay.dtos.transactions.TransactionResponseDTO;
import picpay.picpay.mapper.TransactionMapper;
import picpay.picpay.models.transactions.Transaction;
import picpay.picpay.models.user.User;
import picpay.picpay.repositories.TransactionRepository;
import picpay.picpay.repositories.UserRepository;

@Service
public class TransactionService {
  private final UserRepository userRepository;
  private final TransactionMapper mapper;
  private final TransactionRepository repository;

  public TransactionService(UserRepository userRepository,
                            TransactionMapper mapper,
                            TransactionRepository repository
  ) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.repository = repository;
  }

  @Transactional
  public TransactionResponseDTO createTransaction(String senderId, String receiverId, TransactionRequestDTO request) {
    User sender = this.userRepository.findById(senderId).orElseThrow(() -> new EntityNotFoundException("User not found."));;
    User receiver = this.userRepository.findById(receiverId).orElseThrow(() -> new EntityNotFoundException("User not found."));;

    Transaction transaction = this.mapper.toEntity(request);

    transaction.setSender(sender);
    transaction.setReceiver(receiver);

    this.repository.save(transaction);

    return this.mapper.toResponse(transaction);
  }

  public List<TransactionResponseDTO> getAllTransactions() {
    return this.repository.findAll()
                          .stream()
                          .map(this.mapper::toResponse)
                          .toList();
  }
}
