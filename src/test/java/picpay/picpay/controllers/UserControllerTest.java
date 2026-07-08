package picpay.picpay.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import picpay.picpay.dtos.user.UserRegisterRequestDTO;
import picpay.picpay.dtos.user.UserResponseDTO;
import picpay.picpay.models.user.UserType;
import picpay.picpay.services.users.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService service;

  @Autowired
  private ObjectMapper objectMapper;

  private UserResponseDTO buildUserResponseDTO() {
    return new UserResponseDTO("51c92414-92cb-419a-92ec-63e166ab6a76", 
                                "Daniel", 
                                "Shimabukuro", 
                                BigDecimal.valueOf(1000),
                                UserType.COMMON);
  }
}
