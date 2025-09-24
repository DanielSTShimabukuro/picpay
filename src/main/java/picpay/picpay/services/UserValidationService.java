package picpay.picpay.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import picpay.picpay.dtos.user.RegisterDTO;
import picpay.picpay.repositories.UserRepository;

@Service
public class UserValidationService {
    private UserRepository repository;

    public UserValidationService(UserRepository repository) {
        this.repository = repository;
    }

    private void validateRegister(RegisterDTO request) {
        if (this.repository.existsByCpf(request.cpf())) {
            throw new DataIntegrityViolationException("Cpf is already used");
        }
        
        if (this.repository.existsByEmail(request.email())) {
            throw new DataIntegrityViolationException("Email is alredy used.");
        }
    }
}
