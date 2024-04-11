package com.example.tuned.Business.Impl.UserUseCaseImpl;

import com.example.tuned.Business.Exceptions.UsernameAlreadyExistsException;
import com.example.tuned.Business.Interfaces.UserInterfaces.CreateUserUseCase;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Domain.User.CreateUserRequest;
import com.example.tuned.Domain.User.CreateUserResponse;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   @Transactional
    @Override
    public CreateUserResponse CreateUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException();
        }
        UserEntity SavedUser = SaveNewUser(request);
        return CreateUserResponse.builder()
                .userId(SavedUser.getUserId())
                .build();
    }


    private UserEntity SaveNewUser(CreateUserRequest request){
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .subscription(SubscriptionType.free)
                .build();
        return userRepository.save(newUser);
    }

}
