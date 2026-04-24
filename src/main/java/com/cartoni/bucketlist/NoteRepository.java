package com.cartoni.bucketlist;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    Set<Note> findByUserId(Integer userId);
}
