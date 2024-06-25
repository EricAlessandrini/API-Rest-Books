package com.ega.books.utils.mappers;

import com.ega.books.exception.exceptions.MappingException;
import com.ega.books.utils.ErrorCatalog;
import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.entity.AuthorEntity;

@Component
public class AuthorMapper {

	public AuthorDTO entityToDTO(AuthorEntity authorEntity) {
		if(authorEntity == null) {
			throw new MappingException(
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode(),
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage()
			);
		}
		return AuthorDTO.builder()
				.id(authorEntity.getId())
				.fullName(authorEntity.getFullName())
				.birthday(authorEntity.getBirthday())
				.placeOfBirth(authorEntity.getPlaceOfBirth())
				.nationality(authorEntity.getNationality())
				.build();
	}
	
	public AuthorEntity dtoToEntity(AuthorDTO authorDTO) {
		if(authorDTO == null) {
			throw new MappingException(
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode(),
					ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage()
			);
		}
		
		return AuthorEntity.builder()
				.fullName(authorDTO.getFullName())
				.birthday(authorDTO.getBirthday())
				.placeOfBirth(authorDTO.getPlaceOfBirth())
				.nationality(authorDTO.getNationality())
				.build();
	}
}
