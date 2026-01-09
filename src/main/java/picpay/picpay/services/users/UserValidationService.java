package picpay.picpay.services.users;

import org.springframework.stereotype.Service;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserUpdateRequestDTO;
import picpay.picpay.exceptions.BusinessException;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserValidationService {
    private final UserRepository repository;

    public UserValidationService(
        UserRepository repository) {
        this.repository = repository;
    }

    public void validateRegister(UserRegisterRequestDTO request) {
        if (this.repository.existsByCpf(request.cpf())) {
            throw new BusinessException("Cpf is already used.");
        }
        
        if (this.repository.existsByEmail(request.email())) {
            throw new BusinessException("Email is already used.");
        }
    }

    public void validateUpdate(UserUpdateRequestDTO request, String id) {
        if (request.email() != null && this.repository.existsByEmailAndIdNot(request.email(), id)) {
            throw new BusinessException("Email is already used.");
        }
    }
}
