package com.ega.books.service.book;

import java.util.List;

import com.ega.books.domain.dto.BookDTO;

public interface IBookService {

	// Buscar por ID
	BookDTO findBookById(Long id);
	
	// Buscar por autor
	
	// Buscar todos los libros
	List<BookDTO> findAllBooks();
	
	// Guardar nuevo libro
	void saveBook(BookDTO bookDTO);
	
	// Editar libro
	void updateBook(Long id, BookDTO bookDTO);
	
	// Borrar libro
	void deleteBook(Long id);
	
}
