package picpay.picpay.mapper;

import org.springframework.stereotype.Component;

import picpay.picpay.dtos.user.UserRequestDTO;
import picpay.picpay.models.user.User;

@Component
public class UserMapper {
  public User toEntity(UserRequestDTO request) {
    User user = new User();

    user.setCpf(request.cpf());
    user.setEmail(request.email());
    user.setFirstName(request.firstName());
    user.setLastName(request.lastName());
    user.setPassword(request.password());
    user.setBalance(request.balance());
    user.setType(request.type());

    return user;
  }
}
