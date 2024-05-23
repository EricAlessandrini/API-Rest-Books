package com.ega.books.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.service.book.IBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

	private final IBookService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> findBookById(@PathVariable("id") Long id) {
		BookDTO bookDTO = service.findBookById(id);
		return new ResponseEntity<>(bookDTO, HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAllBooks() {
		List<BookDTO> allBooks = service.findAllBooks();
		return new ResponseEntity<>(allBooks, HttpStatus.FOUND);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveBook(@Valid @RequestBody BookDTO bookDTO) {
		service.saveBook(bookDTO);
		return new ResponseEntity<>("El libro fue guardado con exito", HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
		service.updateBook(id, bookDTO);
		return new ResponseEntity<>("Los datos del libro han sido modificados con exito", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id) {
		service.deleteBook(id);
		return new ResponseEntity<>("El libro ha sido eliminado con exito", HttpStatus.GONE);
	}
	
}
