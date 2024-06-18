package com.ega.books.controller;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.service.book.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(
		name = "Books",
		description = "Controller for Books"
)
public class BookController {

	private final IBookService service;
	
	// BUSCAR LIBRO POR NOMBRE
	@GetMapping("/title")
	@Operation(
			method = "GET",
			summary = "Get book by title",
			description = "GET method to obtain a single book or a list of books depending of the name searched",
			parameters = {
					@Parameter(
							name = "bookTitle",
							in = ParameterIn.QUERY,
							description = "Title of the book that is being searched",
							required = true
					)
			},
			responses = {
					@ApiResponse(
							description = "Returns a single book's information or a list of books with their respective information",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<BookDTO>> findBookByTitle(@RequestParam String bookTitle) {
		List<BookDTO> booksDTO = service.findBookByTitle(bookTitle);
		return new ResponseEntity<>(booksDTO, HttpStatus.FOUND);
	}
	
	// BUSCAR TODOS LOS LIBROS GUARDADOS
	@GetMapping
	@Operation(
			method = "GET",
			summary = "Get all books",
			description = "GET method to obtain all books saved",
			responses = {
					@ApiResponse(
							description = "Returns a list of books with their respective information and a status code",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<BookDTO>> findAllBooks() {
		List<BookDTO> allBooks = service.findAllBooks();
		return new ResponseEntity<>(allBooks, HttpStatus.FOUND);
	}

	// GUARDAR UN NUEVO LIBRO
	@PostMapping("/save")
	@Operation(
			method = "POST",
			summary = "Save a book",
			description = "POST method to save a new book",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "Request body that contains all the information that the user pretends to save related " +
							"to a book that already read",
					required = true,
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = BookDTO.class)
							)
					}
					),
			responses = {
					@ApiResponse(
							description = "Returns a success message and a status code",
							responseCode = "201"
					)
			}
	)
	public ResponseEntity<String> saveBook(@Valid @RequestBody BookDTO bookDTO) {
		service.saveBook(bookDTO);
		return new ResponseEntity<>("El libro fue guardado con exito", HttpStatus.CREATED);
	}
	
	// ACTUALIZAR UN LIBRO GUARDADO
	@PutMapping("/edit/{id}")
	@Operation(
			method = "PUT",
			summary = "Update book information",
			description = "PUT method to update the information of a book saved",
			parameters = {
					@Parameter(
							name = "id",
							in = ParameterIn.PATH,
							description = "Identifier of the book that has to be updated with the new information that is provided through this method",
							required = true
					)
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "Request body contains the key identifier of the content that has to be updated and the new value",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = BookDTO.class)
							)
					}
			),
			responses = {
					@ApiResponse(
							description = "Returns a success message and a status code",
							responseCode = "200"
					)
			}
	)
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
		service.updateBook(id, bookDTO);
		return new ResponseEntity<>("Los datos del libro han sido modificados con exito", HttpStatus.OK);
	}
	
	// BORRAR UN LIBRO GUARDADO
	@DeleteMapping("/delete/{id}")
	@Operation(
			method = "DELETE",
			summary = "Delete a book by Id",
			description = "DELETE method to remove a book saved",
			parameters = {
					@Parameter(
							name = "id",
							in = ParameterIn.PATH,
							description = "Id of the book that is bound to be removed",
							required = true
					)
			},
			responses = {
					@ApiResponse(
							description = "Returns a success message and a status code",
							responseCode = "200"
					)
			}
	)
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id) {
		service.deleteBook(id);
		return new ResponseEntity<>("El libro ha sido eliminado con exito", HttpStatus.OK);
	}

	// BUSCAR LIBRO POR NOMBRE DE AUTOR
	@GetMapping("/byAuthorName")
	@Operation(
			method = "GET",
			summary = "Get books by author's name",
			description = "GET method to obtain a list of books and their respective information" +
					" based on the name of his author",
			parameters = {
					@Parameter(
							name = "authorName",
							description = "Name of the author that wrote the list of books",
							in = ParameterIn.QUERY,
							required = true
					)
			},
			responses = {
					@ApiResponse(
							description = "Returns a list of books that have the same author with their respective information" +
									" and a status code",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<BookDTO>> getBooksByAuthorName(@RequestParam String authorName) {
		List<BookDTO> booksFound = service.findBooksByAuthorName(authorName);
		return new ResponseEntity<>(booksFound, HttpStatus.FOUND);
	}

	// BUSCAR LIBROS POR GENERO
	@GetMapping("/byGenre")
	@Operation(
			method = "GET",
			summary = "Get books by genre's name",
			description = "GET method to obtain a list of books and their respective information" +
					" based on one of the genres of them",
			parameters = {
					@Parameter(
							name = "genreName",
							description = "Name of the genre of the book",
							in = ParameterIn.QUERY,
							required = true
					)
			},
			responses = {
					@ApiResponse(
							description = "Returns a list of books that have the same genre with their respective information" +
									" and a status code",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<BookDTO>> getBooksByGenreName(@RequestParam String genreName){
		List<BookDTO> booksFound = service.findBooksByGenreName(genreName);
		return new ResponseEntity<>(booksFound, HttpStatus.FOUND);
	}
}
