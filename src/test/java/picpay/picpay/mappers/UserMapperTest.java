package picpay.picpay.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.models.user.User;
import picpay.picpay.models.user.UserType;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
  @InjectMocks
  private UserMapper mapper;

  @Test
  void shouldToEntitySuccefully() {
    UserRegisterRequestDTO request = new UserRegisterRequestDTO("881.302.780-06", 
                                                                "daniel.s.t.shimabukuro@gmail.com", 
                                                                "Daniel", 
                                                                "Shimabukuro", 
                                                                "senha", 
                                                                BigDecimal.valueOf(1000), 
                                                                UserType.COMMON);

    User user = this.mapper.toEntity(request);

    assertEquals(request.cpf(), user.getCpf());
    assertEquals(request.email(), user.getEmail());
    assertEquals(request.firstName(), user.getFirstName());
    assertEquals(request.lastName(), user.getLastName());
    assertEquals(request.password(), user.getPassword());
    assertEquals(request.balance(), user.getBalance());
    assertEquals(request.type(), user.getType());
  }
}
