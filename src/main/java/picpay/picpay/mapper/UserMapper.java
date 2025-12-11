package picpay.picpay.mapper;

import org.springframework.stereotype.Component;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.models.user.User;

@Component
public class UserMapper {
  public User toEntity(UserRegisterRequestDTO request) {
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

  public UserResponseDTO toResponse(User user) {
    UserResponseDTO response = new UserResponseDTO(user.getId(), 
                                                    user.getFirstName(), 
                                                    user.getLastName(),
                                                    user.getBalance(),
                                                    user.getType());

    return response;
  }
}
