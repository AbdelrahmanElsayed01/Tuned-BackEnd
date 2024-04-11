package com.example.tuned.Business.Impl.UserUseCaseImpl;

import com.example.tuned.Business.Exceptions.InvalidCredentialsException;
import com.example.tuned.Business.Interfaces.UserInterfaces.LoginUseCase;
import com.example.tuned.Configuration.Security.token.AccessTokenEncoder;
import com.example.tuned.Configuration.Security.token.Impl.AccessTokenImpl;
import com.example.tuned.Domain.User.LoginRequest;
import com.example.tuned.Domain.User.LoginResponse;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;


    @Transactional
    @Override
    public LoginResponse Login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            throw new InvalidCredentialsException();
        }

        if(!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user.getUserId() != null ? user.getUserId() : null;
        List<String> roles =List.of( user.getSubscription().toString());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), userId, roles));
    }

}

