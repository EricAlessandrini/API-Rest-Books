package com.ega.books.service.genre;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.*;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.persistence.dao.IDatabaseCenter;
import com.ega.books.utils.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService{
	
	private final IDatabaseCenter dbCenter;
	private final GenreMapper genreMapper;
	private final BookMapper bookMapper;

	@Override
	public List<GenreDTO> getAllGenres() {
		return dbCenter.getAllGenres().stream()
				.map(genreEntity -> genreMapper.entityToDTO(genreEntity))
				.collect(Collectors.toList());
	}

	@Override
	public GenreDTO getGenreByName(String name) {
		return genreMapper.entityToDTO(dbCenter.getGenreByName(name));
	}

	@Override
	public Set<BookDTO> getBooksByGenre(String genreName) {
		Set<BookDTO> booksByGenre = new HashSet<>();
		for(BookEntity book : dbCenter.getGenreByName(genreName).getBooks()) {
			BookDTO bookDTO = bookMapper.entityToDTO(book);
			booksByGenre.add(bookDTO);
		}
				
		return booksByGenre;
	}

}
