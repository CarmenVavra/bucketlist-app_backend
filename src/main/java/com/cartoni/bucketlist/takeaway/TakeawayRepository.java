package com.cartoni.bucketlist.takeaway;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TakeawayRepository extends CrudRepository<Takeaway,Integer> {
   Set<Takeaway> findByUserId(Integer userId);
}
