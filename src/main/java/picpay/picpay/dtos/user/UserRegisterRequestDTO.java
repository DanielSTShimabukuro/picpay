package picpay.picpay.dtos.user;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import picpay.picpay.models.user.UserType;

public record UserRegisterRequestDTO(
    @NotBlank(message = "CPF is required.") 
    @CPF 
    String cpf,

    @NotBlank(message = "Email is required.") 
    @Email(message = "Email invalid.") 
    String email,

    @NotBlank(message = "First name is required.") 
    String firstName,

    @NotBlank(message = "Last name is required.") 
    String lastName,

    @NotBlank(message = "Password is required.") 
    String password,

    @NotNull(message = "Balance is required.")
    BigDecimal balance,
     
    @NotNull(message = "Type is required.") 
    UserType type) {
  
}
