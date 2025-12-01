package picpay.picpay.dtos.user;

import java.math.BigDecimal;

import picpay.picpay.models.user.User;
import picpay.picpay.models.user.UserType;

public record UserResponseDTO(
    String id,
    String firstName,
    String lastName,
    BigDecimal balance,
    UserType type
) {
    public UserResponseDTO(User user) {
        this(user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getBalance(),
            user.getType());
    }
}
