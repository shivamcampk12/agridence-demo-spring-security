package com.agridence.microservice.Assignment.Controller;


import com.agridence.microservice.Assignment.Vo.NotePersonal;
import com.agridence.microservice.Assignment.Service.UserServiceInterface;
import com.agridence.microservice.Assignment.Service.NoteServiceInterface;
import com.agridence.microservice.Assignment.Entity.Note;
import com.agridence.microservice.Assignment.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteRestController {

    private final NoteServiceInterface noteServiceInterface;

    private final UserServiceInterface userServiceInterface;

    @PostMapping("/add")
    public ResponseEntity<?> addNote(@RequestBody Note noteRequest, @NonNull HttpServletRequest request) {

        //Getting username from session
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Getting JWT Token from HTTP Headers
        String jwt = request.getHeader("Authorization");

        User user = userServiceInterface.fetchUserByUsername(object.toString());

        if (null == user || null == user.getId()) {
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }
        noteRequest.setUser(user);

        return new ResponseEntity<>(noteServiceInterface.saveNote(noteRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable("noteId") Integer noteId) {

        //Getting username from session
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userServiceInterface.fetchUserByUsername(object.toString());

        Note note = noteServiceInterface.getNoteById(noteId);

        //Checking whether logged in user written this note
        if (null == user || null == note || null == note.getUser() || !note.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>("This note does not belong to " + object.toString(), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(noteServiceInterface.deleteNoteById(noteId), HttpStatus.OK);
    }


    @GetMapping("/getAllNotes")
    public ResponseEntity<?> getAllNotes() {
        //Getting username from session
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userServiceInterface.fetchUserByUsername(object.toString());

        List<Note> notes = noteServiceInterface.getAllByUser_Id(user.getId());
        List<NotePersonal> notesPersonal = new ArrayList<>();
        for (int i = 0; i < notes.size() - 1; i++) {
            notesPersonal.add(new NotePersonal(notes.get(i).getId(), notes.get(i).getTitle()));
        }
        return new ResponseEntity<>(notesPersonal, HttpStatus.OK);
    }

    @GetMapping("/noteDetails/{noteId}")
    public ResponseEntity<?> noteDetails(@PathVariable("noteId") Integer noteId) {
        //Getting username from session
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userServiceInterface.fetchUserByUsername(object.toString());

        Note note = noteServiceInterface.getNoteById(noteId);

        if (null == user || null == user.getId() || null == note || null == note.getId() || !note.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>("This note does not belong to " + object.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }
}
