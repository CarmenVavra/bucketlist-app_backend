package com.cartoni.bucketlist.repositories;

import com.cartoni.bucketlist.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
