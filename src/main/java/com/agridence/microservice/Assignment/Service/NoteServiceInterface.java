package com.agridence.microservice.Assignment.Service;

import com.agridence.microservice.Assignment.Entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteServiceInterface {
    ResponseEntity<?> saveNote(Note note);

    ResponseEntity<?> deleteNoteById(Integer noteId);

    List<Note> getAllByUser_Id(Integer userId);

    Note getNoteById(Integer noteId);
}
