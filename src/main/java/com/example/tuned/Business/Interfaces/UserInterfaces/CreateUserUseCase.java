package com.example.tuned.Business.Interfaces.UserInterfaces;

import com.example.tuned.Domain.User.CreateUserRequest;
import com.example.tuned.Domain.User.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse CreateUser(CreateUserRequest request);
}
