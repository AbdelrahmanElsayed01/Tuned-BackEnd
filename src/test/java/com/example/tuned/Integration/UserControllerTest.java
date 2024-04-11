package com.example.tuned.Integration;

import com.example.tuned.Business.Interfaces.UserInterfaces.CreateUserUseCase;
import com.example.tuned.Business.Interfaces.UserInterfaces.DeleteUserUseCase;
import com.example.tuned.Business.Interfaces.UserInterfaces.LoginUseCase;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Domain.User.CreateUserRequest;
import com.example.tuned.Domain.User.CreateUserResponse;
import com.example.tuned.Domain.User.LoginRequest;
import com.example.tuned.Domain.User.LoginResponse;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockBean
    private LoginUseCase loginUseCase;

    @MockBean
    private UserRepository userRepository;

@BeforeEach
public void setUp() {
    when(createUserUseCase.CreateUser(any(CreateUserRequest.class)))
            .thenReturn(CreateUserResponse.builder().userId(1L).build());

    when(deleteUserUseCase.DeleteUser(1L)).thenReturn(true);

    when(loginUseCase.Login(any(LoginRequest.class)))
            .thenReturn(LoginResponse.builder().accessToken("Login successful").build());

    UserEntity existingUser = UserEntity.builder()
            .userId(1L)
            .username("existingUser")
            .password("password123")
            .subscription(SubscriptionType.free)
            .build();

    when(userRepository.existsByUsername("existingUser")).thenReturn(true);
    when(userRepository.findByUsername("existingUser")).thenReturn(existingUser);
}


    @Test
    void testCreateUserEndpoint() throws Exception {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .name("John Doe")
                .username("john.doe")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        CreateUserResponse response = new ObjectMapper().readValue(content, CreateUserResponse.class);
    }

    @Test
    void testDeleteUserEndpoint() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student with ID 1 was successfully deleted."));
    }

    @Test
    void testLoginEndpoint() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("existingUser", "password123");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").exists());
    }
}
