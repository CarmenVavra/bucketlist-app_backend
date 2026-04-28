package com.cartoni.bucketlist.controller;

import com.cartoni.bucketlist.entities.Activity;
import com.cartoni.bucketlist.services.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activity")
    public ResponseEntity<Activity> getById(@RequestParam Integer id) {
        return activityService.findActivityById(id);
    }

    @GetMapping("/activity/user")
    public ResponseEntity<Iterable<Activity>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        return activityService.findAllActivityByUserId(userId);
    }

    @PostMapping("/activity")
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @PutMapping("/activity")
    public ResponseEntity<Activity> update(@RequestBody Activity activity) {
        return activityService.updateActivity(activity);
    }

    @DeleteMapping("/activity")
    public ResponseEntity<Activity> delete(@RequestParam(value = "id") Integer id) {
        return activityService.deleteActivity(id);
    }
}
