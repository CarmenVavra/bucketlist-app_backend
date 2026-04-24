package com.cartoni.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class TakeawayController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TakeawayRepository takeawayRepository;

    @Autowired
    private ActivityTakeawayRepository activityTakeawayRepository;

    @GetMapping("/takeaway")
    public ResponseEntity<Takeaway> getById(Integer id) {
        try {
            Optional<Takeaway> takeaway = takeawayRepository.findById(id);
            if(takeaway.isPresent()) {
                return new ResponseEntity<>(takeaway.get(), HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/takeaway/user")
    public ResponseEntity<Iterable<Takeaway>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Takeaway> takeaway = takeawayRepository.findByUserId(userId);
            if(takeaway != null) {
                return new ResponseEntity<>(takeaway, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/takeaway/create")
    public ResponseEntity<Takeaway> create(@RequestBody Takeaway takeaway,
                                           @RequestParam Integer activityId) {
        try{
            Takeaway createdTakeaway = takeawayRepository.save(takeaway);

            ActivityTakeaway activityTakeaway = new ActivityTakeaway();
            activityTakeaway.setActivityId(activityId);
            activityTakeaway.setTakeawayId(createdTakeaway.getId());
            ActivityTakeaway cratedActivityTakeaway = activityTakeawayRepository.save(activityTakeaway);

            return new ResponseEntity<>(createdTakeaway, HttpStatus.CREATED);
            }catch(Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/takeaway")
    public ResponseEntity<Takeaway> update(@RequestBody Takeaway takeaway) {
        try {
            takeawayRepository.save(takeaway);
            return new ResponseEntity<>(takeaway, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/takeaway")
    public ResponseEntity<Takeaway> delete(@RequestParam(value = "id") Integer id) {
        try {
            takeawayRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
