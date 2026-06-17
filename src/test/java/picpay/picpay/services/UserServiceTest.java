package picpay.picpay.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.mapper.UserMapper;
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
    UserRegisterRequestDTO request = new UserRegisterRequestDTO("881.302.780-06", 
                                                                "daniel.s.t.shimabukuro@gmail.com", 
                                                                "Daniel", 
                                                                "Shimabukuro", 
                                                                "senha", 
                                                                BigDecimal.valueOf(1000), 
                                                                UserType.COMMON);

    User user = new User();

    when(this.mapper.toEntity(request)).thenReturn(user);
    
    this.service.registerUser(request);

    verify(this.mapper).toEntity(request);
    verify(this.validationService).validateRegister(request);
    verify(this.repository).save(user);
    verify(this.mapper).toResponse(user);
  }
}