package com.ega.books.service.genre;

import java.util.List;
import java.util.Set;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.dto.GenreDTO;

public interface IGenreService {

	List<GenreDTO> getAllGenres();
	
	GenreDTO getGenreByName(String name);
	
	Set<BookDTO> getBooksByGenre(String genreName);
}
