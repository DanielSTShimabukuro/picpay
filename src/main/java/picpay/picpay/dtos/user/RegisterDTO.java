package picpay.picpay.dtos.user;

import java.math.BigDecimal;

import picpay.picpay.models.user.UserType;

public record RegisterDTO(String cpf, String email, String firstName, String lastName, String password, BigDecimal balance, UserType type) {
  
}
