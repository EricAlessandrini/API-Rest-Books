package com.ega.books.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.dto.BookDTO;
import com.ega.books.service.author.IAuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
	
	private final IAuthorService authorService;
	
	@GetMapping("/authorName")
	public ResponseEntity<AuthorDTO> getAuthorByName(@RequestBody String authorName) {
		return new ResponseEntity<>(authorService.getAuthorByName(authorName), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
		return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.FOUND);
	}
	
	@GetMapping("/books")
	public ResponseEntity<Set<BookDTO>> getBooksByAuthor(@RequestBody String authorName) {
		return new ResponseEntity<>(authorService.getBooksByAuthor(authorName), HttpStatus.FOUND);
	}
	
	@PutMapping("/authorInfo")
	public ResponseEntity<String> completeAuthorInfo(@RequestBody AuthorDTO authorDTO) {
		authorService.completeAuthorInfo(authorDTO);
		return new ResponseEntity<>("Los datos del autor han sido actualizados", HttpStatus.OK);
	}

}
