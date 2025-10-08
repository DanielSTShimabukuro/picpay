package picpay.picpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import picpay.picpay.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByCpf(String cpf);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(String id);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
