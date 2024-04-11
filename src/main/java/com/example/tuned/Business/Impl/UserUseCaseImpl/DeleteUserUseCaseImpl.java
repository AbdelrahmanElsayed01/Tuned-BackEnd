package com.example.tuned.Business.Impl.UserUseCaseImpl;

import com.example.tuned.Business.Interfaces.UserInterfaces.DeleteUserUseCase;
import com.example.tuned.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;


    @Transactional
    @Override
    public boolean DeleteUser(long userId) {
        return this.userRepository.deleteByUserId(userId);
    }
}
