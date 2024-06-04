package com.ega.books;

import java.time.LocalDate;
import java.util.Set;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.domain.entity.BookEntity;

public class DataProviderForTest {
	
	public static AuthorEntity testAuthorEntity() {
		Set<BookEntity> booksWritten = Set.of(BookEntity.builder()
				.id(1L)
				.title("Harry Potter y la piedra filosofal")
				.genre(null)
				.author(null)
				.build());
		
		AuthorEntity authorEntityForTest = new AuthorEntity(
				1L,
				"Pepito Perez",
				LocalDate.of(1994, 04, 29),
				"Belfast, Scotland",
				"British",
				booksWritten
				);
		
		return authorEntityForTest;
	}
	
	public static AuthorDTO testAuthorDTO() {
		Set<String> booksWrittenStrings = Set.of(
				"Harry Potter y la piedra pomez",
				"Harry Potter y la camara no tan secreta",
				"Harry Potter y el prisionero de tu amor",
				"Harry Potter y la copa del mundo"
				);
		
		AuthorDTO authorDTOForTest = AuthorDTO.builder()
				.fullName("Peperina Lopez")
				.birthday(LocalDate.of(1996, 07, 17))
				.placeOfBirth("Lujan, Mendoza")
				.nationality("Mendocinian")
				.booksWritten(booksWrittenStrings)
				.build();
		
		return authorDTOForTest;
	}

}
