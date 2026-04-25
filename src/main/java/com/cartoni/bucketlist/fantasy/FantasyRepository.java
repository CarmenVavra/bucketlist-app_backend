package com.cartoni.bucketlist.fantasy;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FantasyRepository extends CrudRepository<Fantasy, Integer> {
  Set<Fantasy> findByUserId(Integer userId);
  Set<Fantasy> findByUserIdAndAcceptedAndDeniedAndDone(Integer userId, boolean accepted, boolean denied, boolean done);
  Set<Fantasy> findByPublished(boolean published);
  Set<Fantasy> findByUserIdAndDone(Integer userId, boolean done);
  Set<Fantasy> findByUserIdAndAccepted(Integer userId, boolean accepted);
  Set<Fantasy> findByUserIdAndDenied(Integer userId, boolean denied);

}
