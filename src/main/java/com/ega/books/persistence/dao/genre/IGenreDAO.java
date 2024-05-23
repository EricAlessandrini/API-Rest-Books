package com.ega.books.persistence.dao.genre;

import java.util.List;

import com.ega.books.domain.entity.GenreEntity;

public interface IGenreDAO {

	List<GenreEntity> getAllGenres();
	
	GenreEntity getGenreByName(String name);
	
}
