package com.cartoni.bucketlist.activity;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    Set<Activity> findByUserId(Integer userId);
    //Set<Activity> findByIdAndUserId(Integer id, Integer userId);
}
