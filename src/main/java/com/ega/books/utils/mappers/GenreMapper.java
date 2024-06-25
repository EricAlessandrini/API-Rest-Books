package com.ega.books.utils.mappers;

import com.ega.books.exception.exceptions.MappingException;
import com.ega.books.utils.ErrorCatalog;
import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.domain.entity.GenreEntity;

@Component
public class GenreMapper {

	public GenreDTO entityToDTO(GenreEntity genreEntity) {
		if(genreEntity == null) {
			throw new MappingException(
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode(),
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage()
			);
		}
		
		return new GenreDTO(genreEntity.getName(),
				genreEntity.getDescription(),
				genreEntity.getExamples());
	}
	
	public GenreEntity dtoToEntity(GenreDTO genreDTO) {
		if(genreDTO == null) {
			throw new MappingException(
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode(),
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage()
			);
		}
		
		return GenreEntity.builder()
				.name(genreDTO.getName())
				.description(genreDTO.getDescription())
				.examples(genreDTO.getExamples())
				.build();
	}
}
