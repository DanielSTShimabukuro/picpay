package picpay.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import picpay.picpay.models.transactions.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
