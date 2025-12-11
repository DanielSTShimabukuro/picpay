package picpay.picpay.dtos.user;

import picpay.picpay.models.user.UserType;

public record UserUpdateRequestDTO(
  String cpf, 
  String email, 
  String firstName, 
  String lastName, 
  String password, 
  UserType type) {
  
}
