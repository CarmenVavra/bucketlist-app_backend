package com.cartoni.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ActivityTakeawayController {

    @Autowired
    private TakeawayRepository takeawayRepository;

    @Autowired
    private ActivityTakeawayRepository activityTakeawayRepository;
    private Integer activityId;
    private Integer takeawayId;
    private boolean isChecked;

    @GetMapping("/takeaway/activity/byActivityId")
    public ResponseEntity<List<TakeawayWithChecked>> getAllByActivityId(@RequestParam(value = "activityId") Integer activityId) {
        try {
            List<TakeawayWithChecked> takeaways = new ArrayList<>();
            TakeawayWithChecked takeawayWithChecked = new TakeawayWithChecked();
            Iterable<ActivityTakeaway> activityTakeaways = activityTakeawayRepository.findAllByActivityId(activityId);
            activityTakeaways.forEach(activityTakeaway -> {
                Optional<Takeaway> takeaway = takeawayRepository.findById(activityTakeaway.getTakeawayId());
                if(takeaway.isPresent()) {
                    TakeawayWithChecked tawc = this.mapTakeawayWithChecked(takeaway, activityId, activityTakeaway.isChecked());
                    takeaways.add(tawc);
                }
            });
            return new ResponseEntity<>(takeaways, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private TakeawayWithChecked mapTakeawayWithChecked(Optional<Takeaway> takeaway, Integer activityId, boolean isChecked) {
        TakeawayWithChecked takeawayWithChecked = new TakeawayWithChecked();
        if(takeaway.isPresent()) {
            takeawayWithChecked.setId(takeaway.get().getId());
            takeawayWithChecked.setActivityId(activityId);
            takeawayWithChecked.setDescription(takeaway.get().getDescription());
            takeawayWithChecked.setChecked(isChecked);
        }
        return takeawayWithChecked;
    }

    @PatchMapping("/takeaway/activity/setChecked")
    public ResponseEntity<ActivityTakeaway> setChecked(@RequestParam(value = "activityId") Integer activityId,
                                                       @RequestParam(value = "takeawayId") Integer takeawayId,
                                                       @RequestParam(value = "isChecked") boolean isChecked) {

        ActivityTakeaway activityTakeawayItemInDb = activityTakeawayRepository.findByActivityIdAndTakeawayId(activityId, takeawayId);
        if(activityTakeawayItemInDb != null) {
            activityTakeawayItemInDb.setChecked(isChecked);
            activityTakeawayRepository.save(activityTakeawayItemInDb);

            return new ResponseEntity<>(activityTakeawayItemInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
