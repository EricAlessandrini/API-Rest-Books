package com.ega.books.utils;

import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.domain.entity.GenreEntity;

@Component
public class GenreMapper {

	public GenreDTO entityToDTO(GenreEntity genreEntity) {
		return GenreDTO.builder()
				.name(genreEntity.getName())
				.description(genreEntity.getDescription())
				.examples(genreEntity.getExamples())
				.build();
	}
	
	public GenreEntity dtoToEntity(GenreDTO genreDTO) {
		return GenreEntity.builder()
				.name(genreDTO.getName())
				.description(genreDTO.getDescription())
				.examples(genreDTO.getExamples())
				.build();
	}
}
