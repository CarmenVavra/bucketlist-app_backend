package com.cartoni.bucketlist.fantasy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class FantasyService {

    private final FantasyRepository fantasyRepository;

    public FantasyService(FantasyRepository fantasyRepository) {
        this.fantasyRepository = fantasyRepository;
    }

    public ResponseEntity<Fantasy> findFantasyById(Integer id) {
        try {
            Optional<Fantasy> fantasy = fantasyRepository.findById(id);
            return fantasy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Fantasy>> findAllFantasyByUserId(Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndAcceptedAndDeniedAndDone(userId, false, false, false );
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Fantasy>> findAllFantasyByPublished() {
        try {
            Iterable<Fantasy> fantasys = fantasyRepository.findByPublished(true);
            if(fantasys != null) {
                return new ResponseEntity<>(fantasys, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Fantasy>> findAllFantasyByAccepted(Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndAccepted(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Fantasy>> findAllFantasyByDenied(Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndDenied(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Fantasy>> findAllFantasyByDone(Integer userId) {
        try {
            Iterable<Fantasy> fantasy = fantasyRepository.findByUserIdAndDone(userId, true);
            if(fantasy != null) {
                return new ResponseEntity<>(fantasy, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Fantasy> createFantasy(Fantasy fantasy) {
        try {
            fantasyRepository.save(fantasy);
            return new ResponseEntity<>(fantasy, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Fantasy> updateFantasy(Fantasy fantasy) {
        try {
            fantasyRepository.save(fantasy);
            return new ResponseEntity<>(fantasy, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Fantasy> setFantasyPublished(boolean published, Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setPublished(published);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Fantasy> setFantasyAccepted(Integer id) {
        Optional<Fantasy> fantasyItem = fantasyRepository.findById(id);
        if(fantasyItem.isPresent()){
            fantasyItem.get().setAccepted(true);
            fantasyItem.get().setDenied(false);
            Fantasy updatedFantasyItem = fantasyRepository.save(fantasyItem.get());
            return new ResponseEntity<>(updatedFantasyItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Fantasy> setFantasyDenied(Integer id) {
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

    public ResponseEntity<Fantasy> deleteFantasy(Integer id) {
        try {
            fantasyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Fantasy> setFantasyDone(Integer id) {
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
}
