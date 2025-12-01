package picpay.picpay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import picpay.picpay.dtos.user.UserRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO request) {
    UserResponseDTO response = this.service.registerUser(request);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
