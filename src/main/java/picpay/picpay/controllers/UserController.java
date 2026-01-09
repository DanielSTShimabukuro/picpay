package picpay.picpay.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.dtos.user.UserUpdateRequestDTO;
import picpay.picpay.services.users.UserService;


@RestController
@RequestMapping("users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> registerUser(
    @Valid @RequestBody UserRegisterRequestDTO request) {
    UserResponseDTO response = this.service.registerUser(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<UserResponseDTO> response = this.service.getAllUsers();

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
  

  @GetMapping("{id}")
  public ResponseEntity<UserResponseDTO> getUserById(
    @PathVariable String id) {
    UserResponseDTO response = this.service.getUserById(id);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("{id}")
  public ResponseEntity<UserResponseDTO> updateUserById(
    @PathVariable String id, 
    @Valid @RequestBody UserUpdateRequestDTO request) {
    UserResponseDTO response = this.service.updateUserById(request, id);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUserById(
    @PathVariable String id) {
    this.service.deleteUserById(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
