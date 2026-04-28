package com.cartoni.bucketlist.repositories;

import com.cartoni.bucketlist.entities.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    Set<Note> findAllNoteByUserId(Integer userId);
}
