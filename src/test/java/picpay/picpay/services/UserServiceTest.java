package picpay.picpay.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.exceptions.BusinessException;
import picpay.picpay.mappers.UserMapper;
import picpay.picpay.models.user.User;
import picpay.picpay.models.user.UserType;
import picpay.picpay.repositories.UserRepository;
import picpay.picpay.services.users.UserService;
import picpay.picpay.services.users.UserValidationService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserMapper mapper;

  @Mock
  private UserRepository repository;

  @Mock
  private UserValidationService validationService;

  @InjectMocks
  private UserService service;

  @Test
  void shouldRegisterUserSuccessfully() throws Exception {
    UserRegisterRequestDTO request = this.buildUserRegisterRequestDTO();
    User user = new User();

    when(this.mapper.toEntity(request)).thenReturn(user);
    this.service.registerUser(request);

    verify(this.validationService).validateRegister(request);
    verify(this.mapper).toEntity(request);
    verify(this.repository).save(user);
    verify(this.mapper).toResponse(user);
  }

  @Test
  void shouldNotRegisterUserWhenValidationFails() throws Exception {
    UserRegisterRequestDTO request = this.buildUserRegisterRequestDTO();
    User user = new User();

    doThrow(new BusinessException("")).when(this.validationService).validateRegister(request);
    assertThrows(BusinessException.class, () -> this.service.registerUser(request));

    verify(this.validationService).validateRegister(request);
    verify(this.mapper, never()).toEntity(request);
    verify(this.repository, never()).save(user);
    verify(this.mapper, never()).toResponse(user);
  }

  private UserRegisterRequestDTO buildUserRegisterRequestDTO() {
    return new UserRegisterRequestDTO("881.302.780-06", 
                                      "daniel.s.t.shimabukuro@gmail.com", 
                                      "Daniel", 
                                      "Shimabukuro", 
                                      "senha", 
                                      BigDecimal.valueOf(1000), 
                                      UserType.COMMON);
  }
}
