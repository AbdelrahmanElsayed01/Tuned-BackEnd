package com.example.tuned.Controller;

import com.example.tuned.Business.Interfaces.UserInterfaces.CreateUserUseCase;
import com.example.tuned.Business.Interfaces.UserInterfaces.DeleteUserUseCase;
import com.example.tuned.Business.Interfaces.UserInterfaces.LoginUseCase;
import com.example.tuned.Business.Interfaces.UserInterfaces.SwitchSubscriptionUseCase;
import com.example.tuned.Domain.User.CreateUserRequest;
import com.example.tuned.Domain.User.CreateUserResponse;
import com.example.tuned.Domain.User.LoginRequest;
import com.example.tuned.Domain.User.LoginResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final LoginUseCase loginUseCase;
    private final SwitchSubscriptionUseCase switchSubscriptionUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> CreateUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponse response = createUserUseCase.CreateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        boolean deletionSuccessful = deleteUserUseCase.DeleteUser(userId);
        if (deletionSuccessful) {
            return ResponseEntity.ok("Student with ID " + userId + " was successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginSuccessful = loginUseCase.Login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginSuccessful);
    }

    @PutMapping("/{userId}/switchSubscription")
    public ResponseEntity<String> switchSubscription(@PathVariable Long userId) {
        switchSubscriptionUseCase.GoPremium(userId);
        return ResponseEntity.ok("Subscription switched successfully");
    }
}
