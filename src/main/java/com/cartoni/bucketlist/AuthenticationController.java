package com.cartoni.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @GetMapping("/auth")
    public ResponseEntity<User> login(@RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) {
        try{
            Optional<User> userInDb = Optional.ofNullable(authenticationRepository.findByEmailAndPassword(email, password));
            if(userInDb.isPresent()) {
                return new ResponseEntity<>(userInDb.get(), HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth")
    public ResponseEntity<User> register(@RequestBody User user) {
        try{
            User createdUser = authenticationRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
