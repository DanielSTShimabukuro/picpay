package picpay.picpay.repositories;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import picpay.picpay.models.user.User;
import picpay.picpay.models.user.UserType;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
  @Autowired
  private UserRepository repository;
  
  @Test
  void shouldNotAllowDuplicateCPFInDatabase() {
    User user1 = buildUser("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");
    User user2 = buildUser("881.302.780-06", "danielsatoshi.shimabukuro@gmail.com");

    this.repository.saveAndFlush(user1);

    assertThrows(DataIntegrityViolationException.class, () -> {
      this.repository.saveAndFlush(user2);
    });
  }

  @Test
  void shouldNotAllowDuplicateEmailInDatabase() {
    User user1 = buildUser("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");
    User user2 = buildUser("048.556.150-64", "daniel.s.t.shimabukuro@gmail.com");

    this.repository.saveAndFlush(user1);

    assertThrows(DataIntegrityViolationException.class, () -> {
      this.repository.saveAndFlush(user2);
    });
  }

  private User buildUser(String cpf, String email) {
    User user = new User();

    user.setCpf(cpf);
    user.setEmail(email);
    user.setFirstName("Daniel");
    user.setLastName("Shimabukuro");
    user.setPassword("senha");
    user.setBalance(BigDecimal.valueOf(1000));
    user.setType(UserType.COMMON);

    return user;
  }
}
