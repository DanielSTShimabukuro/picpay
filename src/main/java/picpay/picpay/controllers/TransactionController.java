package picpay.picpay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

  public TransactionController(TransactionService service) {
    this.service = service;
  }

  @PostMapping("{senderId}/{receiverId}")
  public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @PathVariable String senderId,
                                                                  @Valid @PathVariable String receiverId,
                                                                  @Valid @RequestBody TransactionRequestDTO request) {
    TransactionResponseDTO response = this.service.createTransaction(senderId, receiverId, request);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
