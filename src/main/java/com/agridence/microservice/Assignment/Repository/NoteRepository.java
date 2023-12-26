package com.agridence.microservice.Assignment.Repository;

import com.agridence.microservice.Assignment.Entity.Note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Note save(Note note);

    Integer deleteNoteById(Integer noteId);

    List<Note> getAllByUser_Id(Integer userId);

    Note getNoteById(Integer noteId);


}
