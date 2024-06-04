package com.ega.books.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ega.books.DataProviderForTest;
import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.exception.exceptions.ImpossibleMappingException;

public class AuthorMapperTest {
	
	private AuthorMapper authorMapper;
	
	@BeforeEach
	void init() {
		this.authorMapper = new AuthorMapper();
	}

	@Test
	@DisplayName("Mapper to DTO")
	void authorMapper_returnDTO() {
		// Given
		AuthorEntity authorAsArgument = DataProviderForTest.testAuthorEntity();
		
		// When
		AuthorDTO authorReturnedAsDTO = authorMapper.entityToDTO(authorAsArgument);
		
		// Then
		assertNotNull(authorReturnedAsDTO);
		assertEquals("Pepito Perez", authorReturnedAsDTO.getFullName());
		assertEquals(LocalDate.of(1994, 04, 29), authorReturnedAsDTO.getBirthday());
		assertEquals("Belfast, Scotland", authorReturnedAsDTO.getPlaceOfBirth());
		assertEquals("British", authorReturnedAsDTO.getNationality());
		assertThat(authorReturnedAsDTO.getBooksWritten()).isNotNull();
	}
	
	@Test
	@DisplayName("Mapper to Entity")
	void authorMapper_returnEntity() {
		// Given
		AuthorDTO authorDTOForTest = DataProviderForTest.testAuthorDTO();
		
		// When 
		AuthorEntity authorReturnedAsEntity = authorMapper.dtoToEntity(authorDTOForTest);
		
		// Then
		assertNotNull(authorReturnedAsEntity);
		assertEquals("Peperina Lopez", authorReturnedAsEntity.getFullName());
		assertEquals(LocalDate.of(1996, 07, 17), authorReturnedAsEntity.getBirthday());
		assertEquals("Lujan, Mendoza", authorReturnedAsEntity.getPlaceOfBirth());
		assertEquals("Mendocinian", authorReturnedAsEntity.getNationality());
		assertNull(authorReturnedAsEntity.getBooksWritten());
	}
	
	@Test
	@DisplayName("Mapper Throw Exceptions")
	void authorMapper_entityToDTO_throwException() {
		// Given
		AuthorEntity authorEntityForException = null;
		AuthorDTO authorDTOForException = null;
		
		// Then
		assertThrows(ImpossibleMappingException.class, 
				() -> authorMapper.entityToDTO(authorEntityForException));
		
		assertThrows(ImpossibleMappingException.class,
				() -> authorMapper.dtoToEntity(authorDTOForException));
	}
}
