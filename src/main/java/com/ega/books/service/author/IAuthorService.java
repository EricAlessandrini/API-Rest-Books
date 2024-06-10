package com.ega.books.service.author;

import java.util.List;

import com.ega.books.domain.dto.AuthorDTO;

public interface IAuthorService {

	// Buscar un autor por nombre
	AuthorDTO getAuthorByName(String authorName);
	
	// Buscar toda la lista de autores
	List<AuthorDTO> getAllAuthors();
	
	void completeAuthorInfo(Long id, AuthorDTO authorDTO);
	
}
