package com.cartoni.bucketlist.repositories;

import com.cartoni.bucketlist.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}
