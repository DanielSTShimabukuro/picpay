package picpay.picpay.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class UserRepositoryTest {
  @Autowired
  private UserRepository repository;
  
  @Test
  void shouldNotAllowDuplicateCPFInDatabase() {
    User user1 = this.buildUser("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");
    User user2 = this.buildUser("881.302.780-06", "danielsatoshi.shimabukuro@gmail.com");

    this.repository.saveAndFlush(user1);

    assertThrows(DataIntegrityViolationException.class, () -> this.repository.saveAndFlush(user2));
  }

  @Test
  void shouldNotAllowDuplicateEmailInDatabase() {
    User user1 = this.buildUser("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");
    User user2 = this.buildUser("048.556.150-64", "daniel.s.t.shimabukuro@gmail.com");

    this.repository.saveAndFlush(user1);

    assertThrows(DataIntegrityViolationException.class, () -> this.repository.saveAndFlush(user2));
  }

  @Test
  void shouldNotAllowNullCpfInDatabase() {
    User user = this.buildUser(null, "daniel.s.t.shimabukuro@gmail.com");

    assertThrows(DataIntegrityViolationException.class, () -> this.repository.saveAndFlush(user));
  }

  @Test
  void shouldNotAllowNullEmailInDatabase() {
    User user = this.buildUser("881.302.780-06", null);

    assertThrows(DataIntegrityViolationException.class, () -> this.repository.saveAndFlush(user));
  }

  @Test
  void shouldFindUserByIdSuccessfully() {
    User user = this.buildUser("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");

    this.repository.saveAndFlush(user);

    assertEquals(user, this.repository.findById(user.getId()).orElse(null));
  }

  @Test
  void shouldNotFindUserById() {
    assertTrue(this.repository.findById("51c92414-92cb-419a-92ec-63e166ab6a76").isEmpty());
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
