package com.ega.books.service.book;

import java.util.List;

import com.ega.books.domain.dto.BookDTO;

public interface IBookService {
	
	//Buscar libro por nombre
	List<BookDTO> findBookByTitle(String bookTitle);

	// Buscar libros por nombre del autor
	List<BookDTO> findBooksByAuthorName(String authorName);
	
	// Buscar libros por genero
	List<BookDTO> findBooksByGenreName(String genreName);
	
	// Buscar todos los libros
	List<BookDTO> findAllBooks();
	
	// Guardar nuevo libro
	void saveBook(BookDTO bookDTO);
	
	// Editar libro
	void updateBook(Long id, BookDTO bookDTO);
	
	// Borrar libro
	void deleteBook(Long id);
	
}
