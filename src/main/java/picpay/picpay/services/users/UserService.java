package picpay.picpay.services.users;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.dtos.user.UserUpdateRequestDTO;
import picpay.picpay.mapper.UserMapper;
import picpay.picpay.models.user.User;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserService {
  private final UserMapper mapper;
  private final UserRepository repository;
  private final UserValidationService validationService;

  public UserService(UserMapper mapper,
    UserRepository repository,
    UserValidationService validationService){
      this.mapper = mapper;
      this.repository = repository;
      this.validationService = validationService;
  }

  @Transactional
  public UserResponseDTO registerUser(UserRegisterRequestDTO request) {
    this.validationService.validateRegister(request);

    User user = this.mapper.toEntity(request);

    this.repository.save(user);

    return this.mapper.toResponse(user);
  }

  public List<UserResponseDTO> getAllUsers() {
    return this.repository.findAll()
                          .stream()
                          .map(this.mapper::toResponse)
                          .toList();
  }

  public UserResponseDTO getUserById(String id) {
    User user = this.repository.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

    return this.mapper.toResponse(user);
  }

  @Transactional
  public UserResponseDTO updateUserById(UserUpdateRequestDTO request, String id) {
    User user = this.repository.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

    user = this.mapper.updateEntity(request, user);
    this.repository.save(user);

    return this.mapper.toResponse(user);
  }

  @Transactional
  public String deleteUserById(String id) {
    User user = this.repository.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

    this.repository.delete(user);

    return "User Deleted.";
  }
}
