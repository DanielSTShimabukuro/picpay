package picpay.picpay.repositories;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import picpay.picpay.models.user.User;
import picpay.picpay.models.user.UserType;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
  @Autowired
  private UserRepository repository;

  @Autowired
  private EntityManager entityManager;

  private User newUser(String cpf, String email, String firstName, String lastName, String password, BigDecimal balance, UserType type) {
    User user = new User();

    user.setCpf(cpf);
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);
    user.setBalance(balance);
    user.setType(type);

    return user;
  }
}
