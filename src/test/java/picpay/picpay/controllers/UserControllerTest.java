package picpay.picpay.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
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

  @Test
  void shouldRegisterUserSuccessfully() throws Exception {
    UserRegisterRequestDTO request = this.buildUserRegisterRequestDTO("881.302.780-06", "daniel.s.t.shimabukuro@gmail.com");
    
    UserResponseDTO response = this.buildUserResponseDTO();

    when(this.service.registerUser(request)).thenReturn(response);

    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(response.id()))
            .andExpect(jsonPath("$.firstName").value(response.firstName()))
            .andExpect(jsonPath("$.lastName").value(response.lastName()))
            .andExpect(jsonPath("$.balance").value(response.balance()))
            .andExpect(jsonPath("$.type").value(response.type().toString()))
            .andExpect(jsonPath("$.cpf").doesNotExist())
            .andExpect(jsonPath("$.email").doesNotExist())
            .andExpect(jsonPath("$.password").doesNotExist());

    verify(this.service).registerUser(request);
  }

  @Test
  void shouldReturnBadRequestWhenInvalidCPFInRegisterUser() throws Exception {
    UserRegisterRequestDTO request = this.buildUserRegisterRequestDTO("1", "daniel.s.t.shimabukuro@gmail.com");

    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.message").value("cpf: invalid Brazilian individual taxpayer registry number (CPF)"))
            .andExpect(jsonPath("$.timestamp").exists());

    verify(service, never()).registerUser(any());
  }

  @Test
  void shouldReturnBadRequestWhenInvalidEmailInRegisterUser() throws Exception {
    UserRegisterRequestDTO request = this.buildUserRegisterRequestDTO("881.302.780-06", "email");

    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.message").value("email: Email invalid."))
            .andExpect(jsonPath("$.timestamp").exists());

    verify(service, never()).registerUser(any());
  }

  private UserRegisterRequestDTO buildUserRegisterRequestDTO(String cpf, String email) {
    return new UserRegisterRequestDTO(cpf, 
                                      email, 
                                      "Daniel", 
                                      "Shimabukuro", 
                                      "senha", 
                                      BigDecimal.valueOf(1000), 
                                      UserType.COMMON);
  }

  private UserResponseDTO buildUserResponseDTO() {
    return new UserResponseDTO("51c92414-92cb-419a-92ec-63e166ab6a76", 
                                "Daniel", 
                                "Shimabukuro", 
                                BigDecimal.valueOf(1000),
                                UserType.COMMON);
  }
}
