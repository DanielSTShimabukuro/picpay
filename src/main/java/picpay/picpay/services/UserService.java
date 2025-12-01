package picpay.picpay.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import picpay.picpay.dtos.user.UserRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.models.user.User;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserService {
  private final UserRepository repository;
  private final UserValidationService validationService;

  public UserService(UserRepository repository,
    UserValidationService validationService){
      this.repository = repository;
      this.validationService = validationService;
  }

  @Transactional
  public UserResponseDTO registerUser(UserRequestDTO request) {
    this.validationService.validateRegister(request);

    User user = new User(request);

    this.repository.save(user);

    return new UserResponseDTO(user);
  }

  public List<User> getAllUsers() {
    return this.repository.findAll();
  }
}
