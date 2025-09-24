package picpay.picpay.dtos.user;

import picpay.picpay.models.user.UserType;

public record UserResponseDTO(
    String id,
    String firstName,
    String lastName,
    String balance,
    UserType type
) {
    
}
