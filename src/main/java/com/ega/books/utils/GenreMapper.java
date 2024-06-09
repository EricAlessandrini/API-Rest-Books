package com.ega.books.utils;

import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.exception.exceptions.ImpossibleMappingException;

@Component
public class GenreMapper {

	public GenreDTO entityToDTO(GenreEntity genreEntity) {
		if(genreEntity == null) {
			throw new ImpossibleMappingException();
		}
		
		return new GenreDTO(genreEntity.getName(),
				genreEntity.getDescription(),
				genreEntity.getExamples());
	}
	
	public GenreEntity dtoToEntity(GenreDTO genreDTO) {
		if(genreDTO == null) {
			throw new ImpossibleMappingException();
		}
		
		return GenreEntity.builder()
				.name(genreDTO.getName())
				.description(genreDTO.getDescription())
				.examples(genreDTO.getExamples())
				.build();
	}
}
