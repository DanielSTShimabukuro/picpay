package picpay.picpay.dtos.user;

import java.math.BigDecimal;

import picpay.picpay.models.user.UserType;

public record UserResponseDTO(
    String id,
    String firstName,
    String lastName,
    BigDecimal balance,
    UserType type
) {
    
}
