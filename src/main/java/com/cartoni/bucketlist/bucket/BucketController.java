package com.cartoni.bucketlist.bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BucketController {

    @Autowired
    private BucketRepository bucketRepository;

    @GetMapping("/bucketlist")
    public ResponseEntity<Bucket> getById(Integer id) {
        try {
            Optional<Bucket> bucket = bucketRepository.findById(id);
            if(bucket.isPresent()) {
                return new ResponseEntity<>(bucket.get(), HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bucketlist/private")
    public ResponseEntity<Iterable<Bucket>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndAcceptedAndDeniedAndDone(userId, false, false, false);
            if(bucket != null) {
                return new ResponseEntity<>(bucket, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/bucketlist/public")
    public ResponseEntity<Iterable<Bucket>> getAllByPublished() {
        try {
            Iterable<Bucket> buckets = bucketRepository.findByPublished(true);
            if(buckets != null) {
                return new ResponseEntity<>(buckets, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bucketlist/byAccepted")
    public ResponseEntity<Iterable<Bucket>> getAllByAccepted(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndAccepted(userId, true);
            if(bucket != null) {
                return new ResponseEntity<>(bucket, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bucketlist/byDenied")
    public ResponseEntity<Iterable<Bucket>> getAllByDenied(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndDenied(userId, true);
            if(bucket != null) {
                return new ResponseEntity<>(bucket, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bucketlist/byDone")
    public ResponseEntity<Iterable<Bucket>> getAllByDone(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndDone(userId, true);
            if(bucket != null) {
                return new ResponseEntity<>(bucket, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/bucketlist")
    public ResponseEntity<Bucket> create(@RequestBody Bucket bucket) {
        try {
            bucketRepository.save(bucket);
            return new ResponseEntity<>(bucket, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/bucketlist")
    public ResponseEntity<Bucket> update(@RequestBody Bucket bucket) {
        try {
            bucketRepository.save(bucket);
            return new ResponseEntity<>(bucket, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/bucketlist/togglePublished")
    public ResponseEntity<Bucket> setPublished(@RequestParam(value = "published") boolean published,
                                               @RequestParam(value = "id") Integer id) {
        Optional<Bucket> bucketItem = bucketRepository.findById(id);
        if(bucketItem.isPresent()){
            bucketItem.get().setPublished(published);
            Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
            return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/bucketlist/setAccepted")
    public ResponseEntity<Bucket> setAccepted(@RequestParam(value = "id") Integer id) {
        Optional<Bucket> bucketItem = bucketRepository.findById(id);
        if(bucketItem.isPresent()){
            bucketItem.get().setAccepted(true);
            bucketItem.get().setDenied(false);
            Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
            return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/bucketlist/setDenied")
    public ResponseEntity<Bucket> setDenied(@RequestParam(value = "id") Integer id) {
        Optional<Bucket> bucketItem = bucketRepository.findById(id);
        if(bucketItem.isPresent()){
            bucketItem.get().setDenied(true);
            bucketItem.get().setAccepted(false);
            bucketItem.get().setPublished(false);
            Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
            return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/bucketlist/setDone")
    public ResponseEntity<Bucket> setDone(@RequestParam(value = "id") Integer id) {
        Optional<Bucket> bucketItem = bucketRepository.findById(id);
        if(bucketItem.isPresent()){
            bucketItem.get().setDone(true);
            bucketItem.get().setAccepted(false);
            bucketItem.get().setPublished(false);
            bucketItem.get().setDenied(false);
            Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
            return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/bucketlist")
    public ResponseEntity<Bucket> delete(@RequestParam(value = "id") Integer id) {
        try {
            bucketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
