package com.cartoni.bucketlist;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ActivityTakeawayRepository extends CrudRepository<ActivityTakeaway, Integer> {
    Set<ActivityTakeaway> findAllByActivityId(Integer activityId);
    ActivityTakeaway findByActivityIdAndTakeawayId(Integer activityId, Integer takeawayId);
}
