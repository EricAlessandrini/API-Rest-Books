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
	
	// BUSCAR LIBRO POR NOMBRE
	@GetMapping("/title")
	public ResponseEntity<List<BookDTO>> findBookByName(@RequestBody String bookTitle) {
		List<BookDTO> booksDTO = service.findBookByTitle(bookTitle);
		return new ResponseEntity<>(booksDTO, HttpStatus.FOUND);
	}
	
	// BUSCAR TODOS LOS LIBROS GUARDADOS
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAllBooks() {
		List<BookDTO> allBooks = service.findAllBooks();
		return new ResponseEntity<>(allBooks, HttpStatus.FOUND);
	}

	// GUARDAR UN NUEVO LIBRO
	@PostMapping("/save")
	public ResponseEntity<String> saveBook(@Valid @RequestBody BookDTO bookDTO) {
		service.saveBook(bookDTO);
		return new ResponseEntity<>("El libro fue guardado con exito", HttpStatus.CREATED);
	}
	
	// ACTUALIZAR UN LIBRO GUARDADO
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
		service.updateBook(id, bookDTO);
		return new ResponseEntity<>("Los datos del libro han sido modificados con exito", HttpStatus.OK);
	}
	
	// BORRAR UN LIBRO GUARDADO
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id) {
		service.deleteBook(id);
		return new ResponseEntity<>("El libro ha sido eliminado con exito", HttpStatus.GONE);
	}

	// BUSCAR LIBRO POR NOMBRE DE AUTOR
	@GetMapping("/byAuthorName")
	public ResponseEntity<List<BookDTO>> getBooksByAuthorName(@RequestBody String authorName) {
		List<BookDTO> booksFound = service.findBooksByAuthorName(authorName);
		return new ResponseEntity<>(booksFound, HttpStatus.FOUND);
	}

	// BUSCAR LIBROS POR GENERO
	/*@GetMapping("/byGenre")
	public ResponseEntity<List<BookDTO>> getBooksByGenreName(@RequestBody String genreName){
		List<BookDTO> booksFound = service.findBooksByGenreName(genreName);
		return new ResponseEntity<>(booksFound, HttpStatus.FOUND);
	}*/
}
