package picpay.picpay.services.users;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import picpay.picpay.dtos.user.UserRequestDTO;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserValidationService {
    private UserRepository repository;

    public UserValidationService(UserRepository repository) {
        this.repository = repository;
    }

    public void validateRegister(UserRequestDTO request) {
        if (this.repository.existsByCpf(request.cpf())) {
            throw new DataIntegrityViolationException("Cpf is already used");
        }
        
        if (this.repository.existsByEmail(request.email())) {
            throw new DataIntegrityViolationException("Email is alredy used.");
        }
    }
}
