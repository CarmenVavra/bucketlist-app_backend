package com.cartoni.bucketlist.auth;

import com.cartoni.bucketlist.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/auth")
    public ResponseEntity<User> login(@RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) {
        return authenticationService.login(email, password);
    }

    @PostMapping("/auth")
    public ResponseEntity<User> register(@RequestBody User user) {
        return authenticationService.register(user);
    }
}
