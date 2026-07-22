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

  @Test
  void shouldRegisterUserSuccessfully() throws Exception {
    UserRegisterRequestDTO request = new UserRegisterRequestDTO("881.302.780-06", 
                                                                    "daniel.s.t.shimabukuro@gmail.com", 
                                                                    "Daniel", 
                                                                    "Shimabukuro", 
                                                                    "senha", 
                                                                    BigDecimal.valueOf(1000), 
                                                                    UserType.COMMON);
    
    UserResponseDTO response = this.buildUserResponseDTO();

    when(this.service.registerUser(request)).thenReturn(response);

    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(response.id()))
            .andExpect(jsonPath("$.firstName").value(response.firstName()))
            .andExpect(jsonPath("$.lastName").value(response.lastName()))
            .andExpect(jsonPath("$.balance").value(response.balance()))
            .andExpect(jsonPath("$.type").value(response.type().toString()));

    verify(this.service).registerUser(request);
  }

  private UserResponseDTO buildUserResponseDTO() {
    return new UserResponseDTO("51c92414-92cb-419a-92ec-63e166ab6a76", 
                                "Daniel", 
                                "Shimabukuro", 
                                BigDecimal.valueOf(1000),
                                UserType.COMMON);
  }
}
