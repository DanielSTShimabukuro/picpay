package picpay.picpay.services;

import org.springframework.stereotype.Service;

import picpay.picpay.dtos.user.RegisterRequestDTO;
import picpay.picpay.models.user.User;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserService {
  private UserRepository repository;
  private UserValidationService validationService;

  public UserService(
    UserRepository repository,
    UserValidationService validationService){
      this.repository = repository;
      this.validationService = validationService;
  }

  public User registerUser(RegisterRequestDTO request) {
    this.validationService.validateRegister(request);

    User user = new User(request);

    this.repository.save(user);

    return user;
  }
}
