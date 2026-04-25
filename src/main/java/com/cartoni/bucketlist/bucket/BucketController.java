package com.cartoni.bucketlist.bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BucketController {

    @Autowired
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/bucketlist")
    public ResponseEntity<Bucket> getById(@RequestParam(value = "id") Integer id) {
        return bucketService.findBucketById(id);
    }

    @GetMapping("/bucketlist/private")
    public ResponseEntity<Iterable<Bucket>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        return  bucketService.findAllBucketByUserId(userId);
    }

    @GetMapping("/bucketlist/public")
    public ResponseEntity<Iterable<Bucket>> getAllByPublished() {
        return bucketService.findAllBucketByPublished();
    }

    @GetMapping("/bucketlist/byAccepted")
    public ResponseEntity<Iterable<Bucket>> getAllByAccepted(@RequestParam(value = "userId") Integer userId) {
        return bucketService.findAllBucketByUserIdAndAccepted(userId);
    }

    @GetMapping("/bucketlist/byDenied")
    public ResponseEntity<Iterable<Bucket>> getAllByDenied(@RequestParam(value = "userId") Integer userId) {
        return bucketService.findAllBucketByUserIdAndDenied(userId);
    }

    @GetMapping("/bucketlist/byDone")
    public ResponseEntity<Iterable<Bucket>> getAllByDone(@RequestParam(value = "userId") Integer userId) {
        return bucketService.findAllBucketByUserIdAndDone(userId);
    }

    @PostMapping("/bucketlist")
    public ResponseEntity<Bucket> create(@RequestBody Bucket bucket) {
        return bucketService.createBucket(bucket);
    }

    @PutMapping("/bucketlist")
    public ResponseEntity<Bucket> update(@RequestBody Bucket bucket) {
        return bucketService.updateBucket(bucket);
    }

    @PatchMapping("/bucketlist/togglePublished")
    public ResponseEntity<Bucket> setPublished(@RequestParam(value = "published") boolean published,
                                               @RequestParam(value = "id") Integer id) {
        return bucketService.setBucketPublished(published, id);
    }

    @PatchMapping("/bucketlist/setAccepted")
    public ResponseEntity<Bucket> setAccepted(@RequestParam(value = "id") Integer id) {
        return bucketService.setBucketAccepted(id);
    }

    @PatchMapping("/bucketlist/setDenied")
    public ResponseEntity<Bucket> setDenied(@RequestParam(value = "id") Integer id) {
        return bucketService.setBucketDenied(id);
    }

    @PatchMapping("/bucketlist/setDone")
    public ResponseEntity<Bucket> setDone(@RequestParam(value = "id") Integer id) {
        return bucketService.setBucketDone(id);
    }

    @DeleteMapping("/bucketlist")
    public ResponseEntity<Bucket> delete(@RequestParam(value = "id") Integer id) {
        return bucketService.deleteBucket(id);
    }
}
