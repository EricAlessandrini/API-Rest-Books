package com.ega.books.utils;

import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.exception.exceptions.ImpossibleMappingException;

@Component
public class AuthorMapper {

	public AuthorDTO entityToDTO(AuthorEntity authorEntity) {
		if(authorEntity == null) {
			throw new ImpossibleMappingException();
		}
		return AuthorDTO.builder()
				.fullName(authorEntity.getFullName())
				.birthday(authorEntity.getBirthday())
				.placeOfBirth(authorEntity.getPlaceOfBirth())
				.nationality(authorEntity.getNationality())
				.build();
	}
	
	public AuthorEntity dtoToEntity(AuthorDTO authorDTO) {
		if(authorDTO == null) {
			throw new ImpossibleMappingException();
		}
		
		return AuthorEntity.builder()
				.fullName(authorDTO.getFullName())
				.birthday(authorDTO.getBirthday())
				.placeOfBirth(authorDTO.getPlaceOfBirth())
				.nationality(authorDTO.getNationality())
				.build();
	}
}
