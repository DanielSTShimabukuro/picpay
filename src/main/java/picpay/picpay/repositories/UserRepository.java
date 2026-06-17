package picpay.picpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import picpay.picpay.models.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByCpf(String cpf);
    Optional<User> findUserByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, String id);
}
