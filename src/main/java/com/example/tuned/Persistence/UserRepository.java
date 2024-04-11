package com.example.tuned.Persistence;

import com.example.tuned.Persistence.Entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
    boolean deleteByUserId(long userId);
    UserEntity findByUsername(String username);
    @NotNull Optional<UserEntity> findById(@NotNull Long userId);
}
