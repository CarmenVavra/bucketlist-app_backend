package com.cartoni.bucketlist.fantasy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FantasyController {

    @Autowired
    private FantasyRepository fantasyRepository;

    @GetMapping("/fantasy")
    public ResponseEntity<Fantasy> getById(Integer id) {
        try {
            Optional<Fantasy> fantasy = fantasyRepository.findById(id);
            if(fantasy.isPresent()) {
                return new ResponseEntity<>(fantasy.get(), HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fantasy/private")
    public ResponseEntity<Iterable<Fantasy>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndAcceptedAndDeniedAndDone(userId, false, false, false );
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fantasy/public")
    public ResponseEntity<Iterable<Fantasy>> getAllByPublished() {
        try {
            Iterable<Fantasy> fantasys = fantasyRepository.findByPublished(true);
            if(fantasys != null) {
                return new ResponseEntity<>(fantasys, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fantasy/byAccepted")
    public ResponseEntity<Iterable<Fantasy>> getAllByAccepted(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndAccepted(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fantasy/byDenied")
    public ResponseEntity<Iterable<Fantasy>> getAllByDenied(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndDenied(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fantasy/byDone")
    public ResponseEntity<Iterable<Fantasy>> getAllByDone(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndDone(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/fantasy")
    public ResponseEntity<Fantasy> create(@RequestBody Fantasy fantasy) {
        try {
            fantasyRepository.save(fantasy);
            return new ResponseEntity<>(fantasy, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/fantasy")
    public ResponseEntity<Fantasy> update(@RequestBody Fantasy fantasy) {
        try {
            fantasyRepository.save(fantasy);
            return new ResponseEntity<>(fantasy, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/fantasy/togglePublished")
    public ResponseEntity<Fantasy> setPublished(@RequestParam(value = "published") boolean published,
                                               @RequestParam(value = "id") Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setPublished(published);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/fantasy/setAccepted")
    public ResponseEntity<Fantasy> setAccepted(@RequestParam(value = "id") Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setAccepted(true);
            fantasyItem.get().setDenied(false);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/fantasy/setDenied")
    public ResponseEntity<Fantasy> setDenied(@RequestParam(value = "id") Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setDenied(true);
            fantasyItem.get().setAccepted(false);
            fantasyItem.get().setPublished(false);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/fantasy/setDone")
    public ResponseEntity<Fantasy> setDone(@RequestParam(value = "id") Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setDone(true);
            fantasyItem.get().setAccepted(false);
            fantasyItem.get().setPublished(false);
            fantasyItem.get().setDenied(false);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fantasy")
    public ResponseEntity<Fantasy> delete(@RequestParam(value = "id") Integer id) {
        try {
            fantasyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}