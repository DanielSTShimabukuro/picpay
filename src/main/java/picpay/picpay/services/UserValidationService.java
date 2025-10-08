package picpay.picpay.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import picpay.picpay.dtos.user.RegisterRequestDTO;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserValidationService {
    private UserRepository repository;

    public UserValidationService(UserRepository repository) {
        this.repository = repository;
    }

    public void validateRegister(RegisterRequestDTO request) {
        if (this.repository.existsByCpf(request.cpf())) {
            throw new DataIntegrityViolationException("Cpf is already used");
        }
        
        if (this.repository.existsByEmail(request.email())) {
            throw new DataIntegrityViolationException("Email is alredy used.");
        }
    }
}
