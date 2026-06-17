package picpay.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import picpay.picpay.models.transactions.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
