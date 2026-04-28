package com.cartoni.bucketlist.services;

import com.cartoni.bucketlist.entities.ActivityTakeaway;
import com.cartoni.bucketlist.repositories.ActivityTakeawayRepository;
import com.cartoni.bucketlist.entities.Takeaway;
import com.cartoni.bucketlist.repositories.TakeawayRepository;
import com.cartoni.bucketlist.models.TakeawayWithChecked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityTakeawayService {

    private final ActivityTakeawayRepository activityTakeawayRepository;
    private final TakeawayRepository takeawayRepository;

    public ActivityTakeawayService(ActivityTakeawayRepository activityTakeawayRepository, TakeawayRepository takeawayRepository) {
        this.activityTakeawayRepository = activityTakeawayRepository;
        this.takeawayRepository = takeawayRepository;
    }

    public ResponseEntity<List<TakeawayWithChecked>> findAllActTakeByActivityId(Integer activityId) {
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

    public ResponseEntity<ActivityTakeaway> setChecked(Integer activityId,
                                                       Integer takeawayId,
                                                       boolean isChecked) {

        ActivityTakeaway activityTakeawayItemInDb = activityTakeawayRepository.findByActivityIdAndTakeawayId(activityId, takeawayId);
        if(activityTakeawayItemInDb != null) {
            activityTakeawayItemInDb.setChecked(isChecked);
            activityTakeawayRepository.save(activityTakeawayItemInDb);

            return new ResponseEntity<>(activityTakeawayItemInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
