package com.cartoni.bucketlist;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BucketRepository extends CrudRepository<Bucket, Integer> {
    Set<Bucket> findByUserIdAndAcceptedAndDeniedAndDone(Integer userId, boolean accepted, boolean denied, boolean done);
    Set<Bucket> findByPublished(boolean published);
    Set<Bucket> findByUserIdAndDone(Integer userId, boolean done);
    Set<Bucket> findByUserIdAndAccepted(Integer userId, boolean accepted);
    Set<Bucket> findByUserIdAndDenied(Integer userId, boolean denied);
}
