package com.ega.books.service.genre;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.persistence.dao.IDatabaseCenter;
import com.ega.books.utils.GenreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService{
	
	private final IDatabaseCenter dbCenter;
	private final GenreMapper genreMapper;

	@Override
	public List<GenreDTO> getAllGenres() {
		return dbCenter.getAllGenres().stream()
				.map(genreMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public GenreDTO getGenreByName(String name) {
		return genreMapper.entityToDTO(dbCenter.getGenreByName(name));
	}
	
}
