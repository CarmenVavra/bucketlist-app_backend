package com.cartoni.bucketlist.note;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note")
    public ResponseEntity<Note> getNoteById(@RequestParam(value = "id") Integer id) {
        return noteService.findNoteById(id);
    }

    @GetMapping("/note/user")
    public ResponseEntity<Iterable<Note>> getAllByUserId(@RequestParam(value = "userId") Integer userId) {
        return noteService.findAllByUserId(userId);
    }

    @PostMapping("/note")
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/note")
    public ResponseEntity<Note> update(@RequestBody Note note) {
        return noteService.updateNote(note);
    }

    @DeleteMapping("/note")
    public ResponseEntity<Note> delete(@RequestParam(value = "id") Integer id) {
        return noteService.deleteNote(id);
    }
}
