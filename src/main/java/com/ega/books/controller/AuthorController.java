package com.ega.books.controller;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.service.author.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	@PutMapping("/editAuthorInfo/{id}")
	public ResponseEntity<String> completeAuthorInfo(@PathVariable("id") Long id,
													 @RequestBody AuthorDTO authorDTO) {
		authorService.completeAuthorInfo(id, authorDTO);
		return new ResponseEntity<>("Los datos del autor han sido actualizados", HttpStatus.OK);
	}
}
