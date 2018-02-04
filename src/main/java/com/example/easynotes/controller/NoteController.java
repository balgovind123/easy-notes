package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {
	@Autowired
	NoteRepository noteRepository;

	// get All the notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	// save a note
	@PostMapping("/notes")
	public Note creatNote(@Valid @RequestBody Note note) {
		System.out.println("inside post method");
		return noteRepository.save(note);
	}

	// get only one id
	@GetMapping(value="/notes/{Id}")
	public ResponseEntity<Note> getNoteById(@PathVariable(value = "Id") long noteId) {
		Note note = noteRepository.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();

		}
		return ResponseEntity.ok().body(note);

	}

}
