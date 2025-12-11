package picpay.picpay.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.services.users.UserService;


@RestController
@RequestMapping("users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegisterRequestDTO request) {
    UserResponseDTO response = this.service.registerUser(request);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<UserResponseDTO> response = this.service.getAllUsers();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

  @GetMapping("{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@Valid @PathVariable String id) {
    UserResponseDTO response = this.service.getUserById(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUserById(@Valid @PathVariable String id) {
    String response = this.service.deleteUserById(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
