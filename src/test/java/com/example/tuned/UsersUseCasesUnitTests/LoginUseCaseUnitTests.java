package com.example.tuned.UsersUseCasesUnitTests;

import com.example.tuned.Business.Exceptions.InvalidCredentialsException;
import com.example.tuned.Business.Impl.UserUseCaseImpl.LoginUseCaseImpl;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Configuration.Security.token.AccessToken;
import com.example.tuned.Configuration.Security.token.AccessTokenEncoder;
import com.example.tuned.Domain.User.LoginRequest;
import com.example.tuned.Domain.User.LoginResponse;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
class LoginUseCaseUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    public LoginUseCaseUnitTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_Successful() {
        // Arrange
        String username = "john doe";
        String password = "123";

        UserEntity user = UserEntity.builder()
                .username(username)
                .password("encodedPassword123")
                .subscription(SubscriptionType.free)
                .build();
        when(userRepository.findByUsername(username)).thenReturn(user);

        when(passwordEncoder.matches(eq(password), anyString())).thenReturn(true);

        AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
        LoginUseCaseImpl loginUseCase = new LoginUseCaseImpl(userRepository, passwordEncoder, accessTokenEncoder);

        // Act
        LoginResponse loginResponse = loginUseCase.Login(new LoginRequest(username, password));

        // Assert
        assertNotNull(loginResponse);

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(eq(password), anyString());
        verify(accessTokenEncoder, times(1)).encode(any(AccessToken.class));
    }

    @Test
    void testLogin_Unsuccessful() {
        // Arrange
        String username = "john.doe";
        String password = "incorrectPassword";

        UserEntity user = UserEntity.builder()
                .username(username)
                .password("encodedPassword123")
                .build();
        when(userRepository.findByUsername(username)).thenReturn(user);

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () ->
                loginUseCase.Login(new LoginRequest(username, password))
        );

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(eq(password), anyString());
    }

}
