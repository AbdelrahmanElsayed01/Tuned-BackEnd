package com.example.tuned.UsersUseCasesUnitTests;

import com.example.tuned.Business.Impl.UserUseCaseImpl.SwitchSubscriptionUseCaseImpl;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SwitchSubscriptionUseCaseUnitTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SwitchSubscriptionUseCaseImpl switchSubscriptionUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGoPremium_UserExistsAndFreeSubscription() {
        Long userId = 1L;
        UserEntity user = UserEntity.builder()
                .userId(userId)
                .name("John Doe")
                .username("jdoe")
                .email("john@example.com")
                .password("123")
                .subscription(SubscriptionType.free)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        switchSubscriptionUseCase.GoPremium(userId);

        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

        // Additional assertions
        assert(user.getSubscription() == SubscriptionType.premium);
        assertEquals("John Doe", user.getName());
        assertEquals("jdoe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("123", user.getPassword());
    }
}
