package com.example.tuned.Business.Interfaces.UserInterfaces;

import com.example.tuned.Domain.User.LoginRequest;
import com.example.tuned.Domain.User.LoginResponse;

public interface LoginUseCase {
    LoginResponse Login(LoginRequest loginRequest);
}
