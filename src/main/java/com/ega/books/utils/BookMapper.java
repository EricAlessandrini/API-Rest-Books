package com.ega.books.utils;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;

@Component
public class BookMapper {

	// BookDTO a BookEntity
	
	public BookEntity dtoToEntity(BookDTO bookDTO) {
		if(bookDTO == null) {
			return null;
		} else {
			return BookEntity.builder()
					.title(bookDTO.getTitle())
					.genre(bookDTO.getGenre().stream()
							.map(name -> GenreEntity.builder()
									.name(name)
									.build())
							.collect(Collectors.toSet()))
					.build();
		}
	}
	
	// BookEntity a BookDTO
	
	  public BookDTO entityToDTO(BookEntity entity) {
		  if(entity == null) {
			  return null;
		  } else {
			  return BookDTO.builder()
					  .id(entity.getId())
					  .title(entity.getTitle())
					  .genre(entity.getGenre()
							  .stream()
							  .map(GenreEntity::getName)
							  .collect(Collectors.toSet()))
					  .build();
		  }  
	  }
	 
}
