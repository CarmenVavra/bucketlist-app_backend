package com.cartoni.bucketlist.controller;

import com.cartoni.bucketlist.services.ActivityTakeawayService;
import com.cartoni.bucketlist.entities.ActivityTakeaway;
import com.cartoni.bucketlist.model.TakeawayWithChecked;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityTakeawayController {

    private final ActivityTakeawayService activityTakeawayService;

    public ActivityTakeawayController(ActivityTakeawayService activityTakeawayService) {
        this.activityTakeawayService = activityTakeawayService;
    }

    @GetMapping("/takeaway/activity/byActivityId")
    public ResponseEntity<List<TakeawayWithChecked>> getAllByActivityId(@RequestParam(value = "activityId") Integer activityId) {
        return activityTakeawayService.findAllActTakeByActivityId(activityId);
    }

    @PatchMapping("/takeaway/activity/setChecked")
    public ResponseEntity<ActivityTakeaway> setChecked(@RequestParam(value = "activityId") Integer activityId,
                                                       @RequestParam(value = "takeawayId") Integer takeawayId,
                                                       @RequestParam(value = "isChecked") boolean isChecked) {

        return activityTakeawayService.setChecked(activityId, takeawayId, isChecked);
    }
}
