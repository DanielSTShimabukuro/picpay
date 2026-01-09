package picpay.picpay.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import picpay.picpay.dtos.transactions.TransactionRequestDTO;
import picpay.picpay.dtos.transactions.TransactionResponseDTO;
import picpay.picpay.services.transactions.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionController {
  private final TransactionService service;

  public TransactionController(
    TransactionService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<TransactionResponseDTO> createTransaction(
    @Valid @RequestBody TransactionRequestDTO request) {
    TransactionResponseDTO response = this.service.createTransaction(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
    List<TransactionResponseDTO> response = this.service.getAllTransactions();

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
