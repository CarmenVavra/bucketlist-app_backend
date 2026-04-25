package com.cartoni.bucketlist.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ResponseEntity<Note> findNoteById(Integer id) {
        try {
            Optional<Note> note = noteRepository.findById(id);
            if(note.isPresent()) {
                return new ResponseEntity<>(note.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<Note>> findAllByUserId(Integer userId) {
        try {
            Iterable<Note> note = noteRepository.findAllNoteByUserId(userId);
            return new ResponseEntity<>(note, HttpStatus.OK);

/*            if(note != null) {
                return new ResponseEntity<>(note, HttpStatus.OK);
            }else{
                return new ResponseEntity("nothing found", HttpStatus.OK);
            }
*/       }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Note> createNote(Note note) {
        try {
            noteRepository.save(note);
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Note> updateNote(Note note) {
        try {
            noteRepository.save(note);
            return new ResponseEntity<>(note, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Note> deleteNote(Integer id) {
        try {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
