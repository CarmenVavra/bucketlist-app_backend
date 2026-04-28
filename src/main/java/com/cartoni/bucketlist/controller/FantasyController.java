package com.cartoni.bucketlist.controller;

import com.cartoni.bucketlist.entities.Fantasy;
import com.cartoni.bucketlist.services.FantasyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FantasyController {

    private final FantasyService fantasyService;

    public FantasyController(FantasyService fantasyService) {
        this.fantasyService = fantasyService;
    }

    @GetMapping("/fantasy")
    public ResponseEntity<Fantasy> getById(@RequestParam (value = "id") Integer id) {
        return fantasyService.findFantasyById(id);
    }

    @GetMapping("/fantasy/private")
    public ResponseEntity<Iterable<Fantasy>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        return  fantasyService.findAllFantasyByUserId(userId);
    }

    @GetMapping("/fantasy/public")
    public ResponseEntity<Iterable<Fantasy>> getAllByPublished() {
        return fantasyService.findAllFantasyByPublished();
    }

    @GetMapping("/fantasy/byAccepted")
    public ResponseEntity<Iterable<Fantasy>> getAllByAccepted(@RequestParam(value = "userId") Integer userId) {
        return fantasyService.findAllFantasyByAccepted(userId);
    }

    @GetMapping("/fantasy/byDenied")
    public ResponseEntity<Iterable<Fantasy>> getAllByDenied(@RequestParam(value = "userId") Integer userId) {
        return  fantasyService.findAllFantasyByDenied(userId);
    }

    @GetMapping("/fantasy/byDone")
    public ResponseEntity<Iterable<Fantasy>> getAllByDone(@RequestParam(value = "userId") Integer userId) {
        return fantasyService.findAllFantasyByDone(userId);
    }

    @PostMapping("/fantasy")
    public ResponseEntity<Fantasy> create(@RequestBody Fantasy fantasy) {
        return fantasyService.createFantasy(fantasy);
    }

    @PutMapping("/fantasy")
    public ResponseEntity<Fantasy> update(@RequestBody Fantasy fantasy) {
        return fantasyService.updateFantasy(fantasy);
    }

    @PatchMapping("/fantasy/togglePublished")
    public ResponseEntity<Fantasy> setPublished(@RequestParam(value = "published") boolean published,
                                               @RequestParam(value = "id") Integer id) {
        return fantasyService.setFantasyPublished(published, id);
    }

    @PatchMapping("/fantasy/setAccepted")
    public ResponseEntity<Fantasy> setAccepted(@RequestParam(value = "id") Integer id) {
        return fantasyService.setFantasyAccepted(id);
    }

    @PatchMapping("/fantasy/setDenied")
    public ResponseEntity<Fantasy> setDenied(@RequestParam(value = "id") Integer id) {
        return fantasyService.setFantasyDenied(id);
    }

    @PatchMapping("/fantasy/setDone")
    public ResponseEntity<Fantasy> setDone(@RequestParam(value = "id") Integer id) {
        return fantasyService.setFantasyDone(id);
    }

    @DeleteMapping("/fantasy")
    public ResponseEntity<Fantasy> delete(@RequestParam(value = "id") Integer id) {
        return fantasyService.deleteFantasy(id);
    }

}