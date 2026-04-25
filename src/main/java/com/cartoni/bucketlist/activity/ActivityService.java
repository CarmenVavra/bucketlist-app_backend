package com.cartoni.bucketlist.activity;

import com.cartoni.bucketlist.activityTakeaway.ActivityTakeawayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ResponseEntity<Activity> findActivityById(Integer id) {
        try {
            return new ResponseEntity<>(activityRepository.findById(id).get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Activity>> findAllActivityByUserId(Integer userId) {
        try {
          return new ResponseEntity<>(activityRepository.findByUserId(userId), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Activity> createActivity(Activity activity) {
        try {
            activityRepository.save(activity);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Activity> updateActivity(Activity activity) {
        try {
            activityRepository.save(activity);
            return new ResponseEntity<>(activityRepository.save(activity), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Activity> deleteActivity(Integer id) {
        try {
            activityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
