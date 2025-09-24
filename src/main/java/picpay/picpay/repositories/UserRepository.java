package picpay.picpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import picpay.picpay.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
