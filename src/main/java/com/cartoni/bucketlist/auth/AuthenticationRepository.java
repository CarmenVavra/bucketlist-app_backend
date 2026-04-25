package com.cartoni.bucketlist.auth;

import com.cartoni.bucketlist.user.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}
