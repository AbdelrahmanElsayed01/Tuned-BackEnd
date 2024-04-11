package com.example.tuned.UsersUseCasesUnitTests;

import com.example.tuned.Business.Impl.UserUseCaseImpl.DeleteUserUseCaseImpl;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
class DeleteUserUseCaseUnitTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    public DeleteUserUseCaseUnitTests() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testDeleteUser() {
        // Arrange
        long userIdToDelete = 1L;

        when(userRepository.deleteByUserId(userIdToDelete)).thenReturn(true);

        // Act
        boolean deletionResult = deleteUserUseCase.DeleteUser(userIdToDelete);

        // Assert
        assertTrue(deletionResult);

        verify(userRepository, times(1)).deleteByUserId(userIdToDelete);
    }

    @Test
    void testDeleteUser_UnsuccessfulDeletion() {
        // Arrange
        long userIdToDelete = 2L;

        when(userRepository.deleteByUserId(userIdToDelete)).thenReturn(false);

        // Act
        boolean deletionResult = deleteUserUseCase.DeleteUser(userIdToDelete);

        // Assert
        assertFalse(deletionResult);

        verify(userRepository, times(1)).deleteByUserId(userIdToDelete);
    }
}
