package com.cartoni.bucketlist.services;

import com.cartoni.bucketlist.repositories.AuthenticationRepository;
import com.cartoni.bucketlist.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public ResponseEntity<User> login(String email, String password) {
        try{
            Optional<User> userInDb = Optional.ofNullable(authenticationRepository.findByEmailAndPassword(email, password));
            return userInDb.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<User> register(User user) {
        try{
            User createdUser = authenticationRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
