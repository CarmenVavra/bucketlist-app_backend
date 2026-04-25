package com.cartoni.bucketlist.takeaway;

import com.cartoni.bucketlist.activityTakeaway.ActivityTakeaway;
import com.cartoni.bucketlist.activityTakeaway.ActivityTakeawayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class TakeawayService {
    private final TakeawayRepository takeawayRepository;
    private final ActivityTakeawayRepository activityTakeawayRepository;

    public TakeawayService(TakeawayRepository takeawayRepository, ActivityTakeawayRepository activityTakeawayRepository) {
        this.takeawayRepository = takeawayRepository;
        this.activityTakeawayRepository = activityTakeawayRepository;
    }

    public ResponseEntity<Takeaway> findByTakeawayId(Integer id) {
        try {
            return new ResponseEntity<>(takeawayRepository.findById(id).get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Takeaway>> findAllTakeawayByUserId(Integer userId) {
        try {
            return new ResponseEntity<>(takeawayRepository.findByUserId(userId), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Takeaway> createTakeaway(Takeaway takeaway, Integer activityId) {
        try{
            Takeaway createdTakeaway = takeawayRepository.save(takeaway);

            ActivityTakeaway activityTakeaway = new ActivityTakeaway();
            activityTakeaway.setActivityId(activityId);
            activityTakeaway.setTakeawayId(createdTakeaway.getId());
            activityTakeawayRepository.save(activityTakeaway);

            return new ResponseEntity<>(createdTakeaway, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Takeaway> updateTakeaway(Takeaway takeaway) {
        try {
            takeawayRepository.save(takeaway);
            return new ResponseEntity<>(takeaway, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Takeaway> deleteTakeaway(Integer id) {
        try {
            takeawayRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
