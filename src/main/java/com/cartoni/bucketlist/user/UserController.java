package com.cartoni.bucketlist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getById(@RequestParam(value = "userId") Integer userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<User>> getAll() {
        return userService.findAllUser();
    }

    @PostMapping("/user")
    public  ResponseEntity<User> save(@RequestBody User user) {
        return  userService.saveUser(user);
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        return  userService.updateUser(user);
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> delete(@RequestParam(value = "id") Integer id) {
        return userService.deleteUser(id);
    }

}
