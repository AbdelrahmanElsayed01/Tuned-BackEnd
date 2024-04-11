package com.example.tuned.PersistenceUnitTests;

import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoUnitTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testExistsByUsername() {
        // Given
        String existingUsername = "existingUser";
        UserEntity user = new UserEntity();
        user.setUsername(existingUsername);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword");

        // When
        userRepository.save(user);

        // Then
        assertTrue(userRepository.existsByUsername(existingUsername));
    }

    @Test
    void testFindByUsername() {
        // Given
        String existingUsername = "existingUser";
        UserEntity user = new UserEntity();
        user.setUsername(existingUsername);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword");

        // When
        userRepository.save(user);

        // Then
        UserEntity foundUser = userRepository.findByUsername(existingUsername);
        assertEquals(existingUsername, foundUser.getUsername());
    }
}