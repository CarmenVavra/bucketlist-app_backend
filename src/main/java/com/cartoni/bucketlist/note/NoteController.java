package com.cartoni.bucketlist.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note")
    public ResponseEntity<Note> getNoteById(Integer id) {
        try {
            Optional<Note> note = noteRepository.findById(id);
            if(note.isPresent()) {
                return new ResponseEntity<>(note.get(), HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/note/user")
    public ResponseEntity<Iterable<Note>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        try {
            Iterable<Note> note = noteRepository.findByUserId(userId);
            if(note != null) {
                return new ResponseEntity<>(note, HttpStatus.OK);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/note")
    public ResponseEntity<Note> create(@RequestBody Note note) {
        try {
            noteRepository.save(note);
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/note")
    public ResponseEntity<Note> update(@RequestBody Note note) {
        try {
            noteRepository.save(note);
            return new ResponseEntity<>(note, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/note")
    public ResponseEntity<Note> delete(@RequestParam(value = "id") Integer id) {
        try {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
