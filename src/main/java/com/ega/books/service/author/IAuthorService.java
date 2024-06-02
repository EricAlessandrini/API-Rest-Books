package com.ega.books.service.author;

import java.util.List;
import java.util.Set;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.dto.BookDTO;

public interface IAuthorService {

	// Buscar un autor por nombre
	AuthorDTO getAuthorByName(String authorName);
	
	// Buscar toda la lista de autores
	List<AuthorDTO> getAllAuthors();
	
	// Buscar toda la lista de libros que pertenecen a un autor
	Set<BookDTO> getBooksByAuthor(String authorName);

	void completeAuthorInfo(AuthorDTO authorDTO);
	
}
