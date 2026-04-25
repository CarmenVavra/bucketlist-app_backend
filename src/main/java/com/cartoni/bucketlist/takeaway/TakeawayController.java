package com.cartoni.bucketlist.takeaway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TakeawayController {

    private final TakeawayService takeawayService;

    public TakeawayController(TakeawayService takeawayService) {
        this.takeawayService = takeawayService;
    }

    @GetMapping("/takeaway")
    public ResponseEntity<Takeaway> getById(@RequestParam(value = "id") Integer id) {
        return takeawayService.findByTakeawayId(id);
    }

    @GetMapping("/takeaway/user")
    public ResponseEntity<Iterable<Takeaway>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        return takeawayService.findAllTakeawayByUserId(userId);
    }

    @PostMapping("/takeaway/create")
    public ResponseEntity<Takeaway> create(@RequestBody Takeaway takeaway,
                                           @RequestParam Integer activityId) {
        return takeawayService.createTakeaway(takeaway, activityId);
    }

    @PutMapping("/takeaway")
    public ResponseEntity<Takeaway> update(@RequestBody Takeaway takeaway) {
        return takeawayService.updateTakeaway(takeaway);
    }

    @DeleteMapping("/takeaway")
    public ResponseEntity<Takeaway> delete(@RequestParam(value = "id") Integer id) {
        return takeawayService.deleteTakeaway(id);
    }
}
