package com.cartoni.bucketlist.activityTakeaway;

import com.cartoni.bucketlist.auth.AuthenticationService;
import com.cartoni.bucketlist.takeaway.Takeaway;
import com.cartoni.bucketlist.takeaway.TakeawayRepository;
import com.cartoni.bucketlist.takeaway.TakeawayWithChecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ActivityTakeawayController {

    private final ActivityTakeawayService activityTakeawayService;

    public ActivityTakeawayController(ActivityTakeawayService activityTakeawayService, AuthenticationService authenticationService) {
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
