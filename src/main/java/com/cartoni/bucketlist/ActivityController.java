package com.cartoni.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityTakeawayRepository activityTakeawayRepository;

    @GetMapping("/activity")
    public ResponseEntity<Activity> getById(Integer id) {
        try {
            Optional<Activity> activity = activityRepository.findById(id);
            if(activity.isPresent()) {
                return new ResponseEntity<>(activity.get(), HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/activity/user")
    public ResponseEntity<Iterable<Activity>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Activity> activity = activityRepository.findByUserId(userId);
            if(activity != null) {
                return new ResponseEntity<>(activity, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/activity")
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        try {
            activityRepository.save(activity);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/activity")
    public ResponseEntity<Activity> update(@RequestBody Activity activity) {
        try {
            activityRepository.save(activity);
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/activity")
    public ResponseEntity<Activity> delete(@RequestParam(value = "id") Integer id) {
        try {
            activityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
