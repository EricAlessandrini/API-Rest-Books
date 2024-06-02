package com.ega.books.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.AuthorEntity;
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
					.author(AuthorEntity.builder()
							.fullName(bookDTO.getAuthor())
							.build())
					.build();
		}
	}
	
	// BookEntity a BookDTO
	
	  public BookDTO entityToDTO(BookEntity entity) {
		  if(entity == null) {
			  return null;
		  } else {
			  Set<String> genres = new HashSet<>();
			  for(GenreEntity genreEntity : entity.getGenre()) {
				  genres.add(genreEntity.getName());
			  }
			  return new BookDTO(entity.getTitle(), genres, entity.getAuthor().getFullName());
		  }  
	  }
	 
}
