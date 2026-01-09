package picpay.picpay.services.transactions;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
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
  private final TransactionValidationService validationService;

  public TransactionService(
    UserRepository userRepository,
    TransactionMapper mapper,
    TransactionRepository repository,
    TransactionValidationService validationService
  ) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.repository = repository;
    this.validationService = validationService;
  }

  @Transactional
  public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
    User sender = this.userRepository.findById(request.senderId()).orElseThrow(() -> new EntityNotFoundException("Sender not found."));
    User receiver = this.userRepository.findById(request.receiverId()).orElseThrow(() -> new EntityNotFoundException("Receiver not found."));

    this.validationService.validateTransaction(sender, request);

    Transaction transaction = this.mapper.toEntity(request);

    sender.setBalance(sender.getBalance().subtract(request.amount()));
    receiver.setBalance(receiver.getBalance().add(request.amount()));

    transaction.setSender(sender);
    transaction.setReceiver(receiver);

    this.repository.save(transaction);

    return this.mapper.toResponse(transaction);
  }

  @Transactional(readOnly = true)
  public List<TransactionResponseDTO> getAllTransactions() {
    return this.repository.findAll()
                          .stream()
                          .map(this.mapper::toResponse)
                          .toList();
  }
}
