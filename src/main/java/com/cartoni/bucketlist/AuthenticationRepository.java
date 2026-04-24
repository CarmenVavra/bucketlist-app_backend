package com.cartoni.bucketlist;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}
