package picpay.picpay.services.users;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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

  public List<UserResponseDTO> getAllUsers() {
    return this.repository.findAll()
                          .stream()
                          .map(UserResponseDTO::new)
                          .toList();
  }

  public UserResponseDTO getUserById(String id) {
    User user = this.repository.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

    return new UserResponseDTO(user);
  }
}
