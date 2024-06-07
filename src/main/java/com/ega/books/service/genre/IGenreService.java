package com.ega.books.service.genre;

import java.util.List;

import com.ega.books.domain.dto.GenreDTO;

public interface IGenreService {

	List<GenreDTO> getAllGenres();
	
	GenreDTO getGenreByName(String name);
}
