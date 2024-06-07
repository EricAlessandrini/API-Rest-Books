package com.ega.books;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ega.books.domain.Genre;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;

public class DataProviderForTest {
	
	// Data for Author Entities
	public static AuthorEntity returnJKRowlingForTest() {
		return AuthorEntity.builder().id(1L).fullName("J.K. Rowling").birthday(LocalDate.of(1965, 07, 31))
				.placeOfBirth("Yate, Reino Unido").nationality("British").build();
	}
	
	public static AuthorEntity returnFrankHerbertForTest() {
		return AuthorEntity.builder().id(2L).fullName("Frank Herbert").birthday(LocalDate.of(1920, 10, 8))
				.placeOfBirth("Tacoma, Washington").nationality("North American").build();
	}
	
	public static AuthorEntity returnAgathaChristieForTest() {
		return AuthorEntity.builder().id(3L).fullName("Agatha Christie").birthday(LocalDate.of(1890, 9, 15))
				.placeOfBirth("Torquay,, Reino Unido").nationality("British").build();
	}
	
	// Data for Genre Entities 
	public static GenreEntity returnFantasyGenreForTest() {
		return GenreEntity.builder().id(1L).name(Genre.FANTASIA.getName())
				.description(Genre.FANTASIA.getDescription()).examples(Genre.FANTASIA.getExamples()).build();
	}
	
	public static GenreEntity returnScienceFictionGenreForTest() {
		return GenreEntity.builder().id(2L).name(Genre.CIENCIA_FICCION.getName())
				.description(Genre.CIENCIA_FICCION.getDescription()).examples(Genre.CIENCIA_FICCION.getExamples())
				.build();
	}
	
	public static GenreEntity returnMisteryGenreForTest() {
		return GenreEntity.builder().id(3L).name(Genre.MISTERIO.getName())
				.description(Genre.MISTERIO.getDescription()).examples(Genre.MISTERIO.getExamples()).build();
	}
	
	public static GenreEntity returnThrillerGenreForTest() {
		return GenreEntity.builder().id(4L).name(Genre.THRILLER.getName())
				.description(Genre.THRILLER.getDescription()).examples(Genre.THRILLER.getExamples()).build();
	}
	
	// Data for Book Entities
	
	public static BookEntity returnHarryPotterPiedraFilosofalForTest() {
		return BookEntity.builder()
				.id(1L)
				.title("Harry Potter y la piedra filosofal")
				.genre(Set.of(returnFantasyGenreForTest(), returnScienceFictionGenreForTest()))
				.author(returnJKRowlingForTest())
				.build();
	}
	
	public static BookEntity returnHarryPotterCamaraSecretaForTest() {
		return BookEntity.builder()
				.id(2L)
				.title("Harry Potter y la camara secreta")
				.genre(Set.of(returnFantasyGenreForTest(), returnScienceFictionGenreForTest()))
				.author(returnJKRowlingForTest())
				.build();
	}
	
	public static BookEntity returnDuneForTest() {
		return BookEntity.builder()
				.id(3L)
				.title("Dune")
				.genre(Set.of(returnFantasyGenreForTest(), returnScienceFictionGenreForTest(), returnThrillerGenreForTest()))
				.author(returnFrankHerbertForTest())
				.build();
	}
	
	public static BookEntity returnAsesinatoExpresoOrienteForTest() {
		return BookEntity.builder()
				.id(4L)
				.title("Asesinato en el Expreso de Oriente")
				.genre(Set.of(returnMisteryGenreForTest(), returnThrillerGenreForTest()))
				.author(returnAgathaChristieForTest())
				.build();
	}
	
}
