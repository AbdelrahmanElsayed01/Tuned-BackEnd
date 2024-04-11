package com.example.tuned.UsersUseCasesUnitTests;

import com.example.tuned.Business.Exceptions.UsernameAlreadyExistsException;
import com.example.tuned.Business.Impl.UserUseCaseImpl.CreateUserUseCaseImpl;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Domain.User.CreateUserRequest;
import com.example.tuned.Domain.User.CreateUserResponse;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .name("John Doe")
                .username("john")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        String encodedPassword = "encodedPassword123"; // A mocked encoded password

        when(passwordEncoder.encode("password123")).thenReturn(encodedPassword); // Mocking password encoding

        when(userRepository.existsByUsername("john")).thenReturn(false);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .name("John Doe")
                .username("john")
                .email("john.doe@example.com")
                .password(encodedPassword) // Using the mocked encoded password
                .subscription(SubscriptionType.free)
                .build();
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // Act
        CreateUserResponse response = createUserUseCase.CreateUser(request);

        // Assert
        verify(userRepository, times(1)).existsByUsername("john");
        verify(userRepository, times(1)).save(any(UserEntity.class));

        assertNotNull(createUserUseCase);
        assertEquals(1L, response.getUserId());
    }


    @Test
    void testCreateUser_UsernameAlreadyExists() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .name("John Doe")
                .username("john")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        when(userRepository.existsByUsername("john")).thenReturn(true);

        // Act and Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> createUserUseCase.CreateUser(request));

        // Verify that save method is not called when username already exists
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
