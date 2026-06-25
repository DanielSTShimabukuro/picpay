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

  @Test
  void shouldNotAllowDuplicateCPFInDatabase() {
    User user1 = newUser("881.302.780-06", 
                          "daniel.s.t.shimabukuro@gmail.com", 
                          "Daniel", 
                          "Shimabukuro", 
                          "senha", 
                          BigDecimal.valueOf(1000), 
                          UserType.COMMON);

    User user2 = newUser("881.302.780-06", 
                          "danielsatoshi.shimabukuro@gmail.com", 
                          "Daniel", 
                          "Shimabukuro", 
                          "senha", 
                          BigDecimal.valueOf(1000), 
                          UserType.COMMON);

    this.entityManager.persist(user1);
    this.entityManager.flush();

    assertThrows(ConstraintViolationException.class, () -> {
      this.entityManager.persist(user2);
      this.entityManager.flush();
    });
  }

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
