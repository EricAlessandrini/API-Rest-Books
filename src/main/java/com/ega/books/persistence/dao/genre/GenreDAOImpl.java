package com.ega.books.persistence.dao.genre;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.persistence.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenreDAOImpl implements IGenreDAO{

	private final GenreRepository genreRepository;
	
	public List<GenreEntity> getAllGenres() {
		return genreRepository.findAll();
	}
	
	public GenreEntity getGenreByName(String name) {
		return genreRepository.findByName(name);
	}
	
}
