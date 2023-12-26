package com.agridence.microservice.Assignment.Service;

import com.agridence.microservice.Assignment.Entity.Note;
import com.agridence.microservice.Assignment.Repository.NoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService implements NoteServiceInterface {

    private final NoteRepository noteRepository;

    @Override
    public ResponseEntity<?> saveNote(Note note) {
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteNoteById(Integer noteId) {
        return new ResponseEntity<>(noteRepository.deleteNoteById(noteId), HttpStatus.OK);
    }

    @Override
    public List<Note> getAllByUser_Id(Integer userId) {
        return noteRepository.getAllByUser_Id(userId);
    }

    @Override
    public Note getNoteById(Integer noteId) {
        return noteRepository.getNoteById(noteId);
    }
}
