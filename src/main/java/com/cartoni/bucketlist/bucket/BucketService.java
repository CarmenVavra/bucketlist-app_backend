package com.cartoni.bucketlist.bucket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class BucketService {
    private final BucketRepository bucketRepository;

    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public ResponseEntity<Bucket> findBucketById(Integer id) {
        try {
            return new ResponseEntity<>(bucketRepository.findById(id).get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Bucket>> findAllBucketByUserId(Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndAcceptedAndDeniedAndDone(userId, false, false, false);
            return new ResponseEntity<>(bucket, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Bucket>> findAllBucketByPublished() {
        try {
            return new ResponseEntity<>(bucketRepository.findByPublished(true), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Bucket>> findAllBucketByUserIdAndAccepted(Integer userId) {
        try {
            Iterable<Bucket> bucket = bucketRepository.findByUserIdAndAccepted(userId, true);
            return new ResponseEntity<>(bucket, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Bucket>> findAllBucketByUserIdAndDenied(Integer userId) {
        try {
            return new ResponseEntity<>(bucketRepository.findByUserIdAndDenied(userId, true), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Bucket>> findAllBucketByUserIdAndDone(Integer userId) {
        try {
            return new ResponseEntity<>(bucketRepository.findByUserIdAndDone(userId, true), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Bucket> createBucket(Bucket bucket) {
        try {
            bucketRepository.save(bucket);
            return new ResponseEntity<>(bucket, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Bucket> updateBucket(Bucket bucket) {
        try {
            bucketRepository.save(bucket);
            return new ResponseEntity<>(bucket, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Bucket> setBucketPublished(boolean published, Integer id) {
        try{
            Optional<Bucket> bucketItem = bucketRepository.findById(id);
            if(bucketItem.isPresent()){
                bucketItem.get().setPublished(published);
                Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
                return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Bucket> setBucketAccepted(Integer id) {
        Optional<Bucket> bucketItem = bucketRepository.findById(id);
        try{
            if(bucketItem.isPresent()){
                bucketItem.get().setAccepted(true);
                bucketItem.get().setDenied(false);
                Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
                return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Bucket> setBucketDenied(Integer id) {
       try{
           Optional<Bucket> bucketItem = bucketRepository.findById(id);
           if(bucketItem.isPresent()){
               bucketItem.get().setDenied(true);
               bucketItem.get().setAccepted(false);
               bucketItem.get().setPublished(false);
               Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
               return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
           }
       }catch(Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Bucket> setBucketDone(Integer id) {
        try{
            Optional<Bucket> bucketItem = bucketRepository.findById(id);
            if(bucketItem.isPresent()){
                bucketItem.get().setDone(true);
                bucketItem.get().setAccepted(false);
                bucketItem.get().setPublished(false);
                bucketItem.get().setDenied(false);
                Bucket updatedBucketItem = bucketRepository.save(bucketItem.get());
                return new ResponseEntity<>(updatedBucketItem, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Bucket> deleteBucket(Integer id) {
        try {
            bucketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
